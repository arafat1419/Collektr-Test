package com.arafat1419.collektr.presentation.ui.features.detail

import androidx.lifecycle.viewModelScope
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionCountdownUseCase
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionDetailsUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.GetAuctionChatBidsUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.GetAuctionHighestBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.SendAuctionBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.SendAuctionChatUseCase
import com.arafat1419.collektr.domain.usecase.favorite.SetFavoriteAuctionUseCase
import com.arafat1419.collektr.presentation.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAuctionDetailsUseCase: GetAuctionDetailsUseCase,
    private val setFavoriteAuctionUseCase: SetFavoriteAuctionUseCase,
    private val getAuctionChatBidsUseCase: GetAuctionChatBidsUseCase,
    private val getHighestBidUseCase: GetAuctionHighestBidUseCase,
    private val sendAuctionBidUseCase: SendAuctionBidUseCase,
    private val sendAuctionChatUseCase: SendAuctionChatUseCase,
    private val getAuctionCountdownUseCase: GetAuctionCountdownUseCase
) : BaseViewModel<DetailViewState, DetailViewEvent>() {
    override fun createInitialState(): DetailViewState = DetailViewState()

    override fun onTriggerEvent(event: DetailViewEvent) {
        when (event) {
            is DetailViewEvent.GetAuctionDetail -> getAuctionDetail(event.auctionId)
            is DetailViewEvent.SetFavoriteAuction -> setFavoriteAuction(
                event.auctionId,
                event.state
            )

            is DetailViewEvent.GetAuctionBids -> getAuctionBids(event.auctionId)
            is DetailViewEvent.GetHighestBid -> getHighestBid(event.auctionId)
            is DetailViewEvent.OnBidAmountChange -> setState { copy(bidAmount = event.amount) }
            is DetailViewEvent.OnChatMessageChange -> setState { copy(chatMessage = event.message) }
            is DetailViewEvent.SendBid -> sendBid(event.auctionId)
            is DetailViewEvent.SendMessage -> sendMessage(event.auctionId, event.message)
            is DetailViewEvent.GetAuctionCountdown -> getAuctionCountdown(event.targetTime)
            DetailViewEvent.ShowPlaceBidBottomSheet -> {
                setState {
                    copy(
                        bidError = "",
                        bidAmount = maxOf(
                            currentState.highestBid.bidAmount,
                            currentState.auction.startBid
                        ) + 10
                    )
                }
                setEvent(DetailViewEvent.ShowPlaceBidBottomSheet)
            }

            DetailViewEvent.HidePlaceBidBottomSheet -> setEvent(DetailViewEvent.HidePlaceBidBottomSheet)
        }
    }

    private fun getAuctionDetail(auctionId: Int) {
        viewModelScope.launch {
            getAuctionDetailsUseCase.invoke(auctionId).collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { setState { copy(isLoading = it) } },
                    setError = { setState { copy(error = it) } },
                ) { data ->
                    setState { copy(auction = data) }
                    onTriggerEvent(DetailViewEvent.GetAuctionBids(auctionId))
                    onTriggerEvent(DetailViewEvent.GetAuctionCountdown(data.auctionEnd))
                }
            }
        }
    }

    private fun getAuctionBids(auctionId: Int) {
        viewModelScope.launch {
            getAuctionChatBidsUseCase.invoke(auctionId).collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { },
                    setError = { setState { copy(error = it) } },
                    { data ->
                        setState {
                            val uniqueBids = data.filter { newBid ->
                                currentState.chatBids.none { existingBid -> existingBid.id == newBid.id && existingBid.isBid == newBid.isBid }
                            }
                            copy(chatBids = uniqueBids + currentState.chatBids)
                        }
                        onTriggerEvent(DetailViewEvent.GetHighestBid(auctionId))
                    }
                )
            }
        }
    }

    private fun getHighestBid(auctionId: Int) {
        viewModelScope.launch {
            getHighestBidUseCase.invoke(auctionId).collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { },
                    setError = { setState { copy(error = it) } },
                    { data ->
                        setState { copy(highestBid = data) }
                    }
                )
            }
        }
    }

    private fun sendBid(auctionId: Int) {
        viewModelScope.launch {
            sendAuctionBidUseCase.invoke(
                ChatBid(
                    auctionId = auctionId,
                    userName = "Arafat Maku",
                    bidAmount = currentState.bidAmount,
                    isBid = true,
                    createdAt = System.currentTimeMillis() / 1000
                ),
                maxOf(currentState.highestBid.bidAmount, currentState.auction.startBid)
            ).also {
                setState { copy(bidError = it) }
                if (it.isEmpty()) {
                    setEvent(DetailViewEvent.HidePlaceBidBottomSheet)
                }
            }
        }
    }

    private fun sendMessage(auctionId: Int, message: String) {
        viewModelScope.launch {
            sendAuctionChatUseCase.invoke(
                ChatBid(
                    auctionId = auctionId,
                    userName = "Arafat Maku",
                    chatMessage = message,
                    isBid = false,
                    createdAt = System.currentTimeMillis() / 1000
                )
            )
            setState { copy(chatMessage = "") }
        }
    }

    private fun setFavoriteAuction(auctionId: Int, state: Boolean) {
        viewModelScope.launch {
            setFavoriteAuctionUseCase.invoke(auctionId, state)
        }
    }

    private fun getAuctionCountdown(targetTime: Long) {
        viewModelScope.launch {
            getAuctionCountdownUseCase.invoke(targetTime).collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { setState { copy(isLoading = it) } },
                    setError = { setState { copy(error = it) } },
                ) { data ->
                    setState { copy(countdown = data) }
                }
            }
        }
    }
}