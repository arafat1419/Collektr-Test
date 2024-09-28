package com.arafat1419.collektr.presentation.ui.features.live

import androidx.lifecycle.viewModelScope
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionDetailsUseCase
import com.arafat1419.collektr.domain.usecase.favorite.SetFavoriteAuctionUseCase
import com.arafat1419.collektr.presentation.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveViewModel @Inject constructor(
    private val getAuctionDetailsUseCase: GetAuctionDetailsUseCase,
    private val setFavoriteAuctionUseCase: SetFavoriteAuctionUseCase
) : BaseViewModel<LiveViewState, LiveViewEvent>() {
    override fun createInitialState(): LiveViewState = LiveViewState()

    override fun onTriggerEvent(event: LiveViewEvent) {
        when (event) {
            is LiveViewEvent.GetAuctionDetail -> getAuctionDetail(event.auctionId)
            is LiveViewEvent.SetFavoriteAuction -> setFavoriteAuction(event.auctionId, event.state)
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
                }
            }
        }
    }

    private fun setFavoriteAuction(auctionId: Int, state: Boolean) {
        viewModelScope.launch {
            setFavoriteAuctionUseCase.invoke(auctionId, state)
        }
    }
}