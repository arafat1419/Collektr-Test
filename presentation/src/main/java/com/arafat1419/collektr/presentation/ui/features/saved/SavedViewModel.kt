package com.arafat1419.collektr.presentation.ui.features.saved

import androidx.lifecycle.viewModelScope
import com.arafat1419.collektr.domain.usecase.favorite.GetFavoriteAuctionUseCase
import com.arafat1419.collektr.presentation.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getFavoriteAuctionUseCase: GetFavoriteAuctionUseCase,
) : BaseViewModel<SavedViewState, SavedViewEvent>() {
    override fun createInitialState(): SavedViewState = SavedViewState()

    override fun onTriggerEvent(event: SavedViewEvent) {
        when (event) {
            is SavedViewEvent.GetSavedAuctions -> getSavedAuctions()
            is SavedViewEvent.NavigateToDetails -> setEvent(SavedViewEvent.NavigateToDetails(event.auction))
        }
    }

    private fun getSavedAuctions() {
        viewModelScope.launch {
            getFavoriteAuctionUseCase.invoke().collect { resource ->
                handleResource(
                    resource = resource,
                    setLoading = { setState { copy(isLoading = it) } },
                    setError = { setState { copy(error = it) } },
                ) {
                    setState { copy(auctions = it) }
                }
            }
        }
    }
}