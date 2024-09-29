package com.arafat1419.collektr.presentation.ui.features.live

import androidx.lifecycle.viewModelScope
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionDetailsUseCase
import com.arafat1419.collektr.domain.usecase.auction.GetLiveAuctionCountUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.GetAuctionChatBidsUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.SendAuctionBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.SendAuctionChatUseCase
import com.arafat1419.collektr.domain.usecase.favorite.SetFavoriteAuctionUseCase
import com.arafat1419.collektr.presentation.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveViewModel @Inject constructor(
    private val getAuctionDetailsUseCase: GetAuctionDetailsUseCase,
    private val getLiveAuctionCountUseCase: GetLiveAuctionCountUseCase,
    private val setFavoriteAuctionUseCase: SetFavoriteAuctionUseCase,
    private val getAuctionChatBidsUseCase: GetAuctionChatBidsUseCase,
    private val sendAuctionBidUseCase: SendAuctionBidUseCase,
    private val sendAuctionChatUseCase: SendAuctionChatUseCase
) : BaseViewModel<LiveViewState, LiveViewEvent>() {
    override fun createInitialState(): LiveViewState = LiveViewState()

    override fun onTriggerEvent(event: LiveViewEvent) {
        when (event) {
            is LiveViewEvent.GetAuctionDetail -> getAuctionDetail(event.auctionId)
            is LiveViewEvent.SetFavoriteAuction -> setFavoriteAuction(event.auctionId, event.state)
            is LiveViewEvent.GetLiveAuctionCount -> getLiveAuctionCount(event.auctionId)
            is LiveViewEvent.GetAuctionBids -> getAuctionBids(event.auctionId)
            is LiveViewEvent.OnChatMessageChange -> setState { copy(chatMessage = event.message) }
            is LiveViewEvent.SendBid -> sendBid(event.auctionId, event.bidAmount)
            is LiveViewEvent.SendMessage -> sendMessage(event.auctionId, event.message)
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
                    onTriggerEvent(LiveViewEvent.GetAuctionBids(auctionId))
                }
            }
        }
    }

    private fun getLiveAuctionCount(auctionId: Int) {
        viewModelScope.launch {
            getLiveAuctionCountUseCase.invoke(auctionId).collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { setState { copy(isLoading = it) } },
                    setError = { setState { copy(error = it) } },
                ) { data ->
                    setState { copy(liveCount = data) }
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
                    }
                )
            }
        }
    }

    private fun sendBid(auctionId: Int, bidAmount: Long) {
        viewModelScope.launch {
            sendAuctionBidUseCase.invoke(
                ChatBid(
                    auctionId = auctionId,
                    userName = "Arafat Maku",
                    bidAmount = bidAmount,
                    isBid = true,
                    createdAt = System.currentTimeMillis() / 1000
                )
            )
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
}