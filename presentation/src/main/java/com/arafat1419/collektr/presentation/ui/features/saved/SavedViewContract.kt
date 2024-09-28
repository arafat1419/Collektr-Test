package com.arafat1419.collektr.presentation.ui.features.saved

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class SavedViewState(
    val auctions: List<Auction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState

sealed class SavedViewEvent : IViewEvent {
    data object GetSavedAuctions : SavedViewEvent()
    data class NavigateToDetails(val auction: Auction) : SavedViewEvent()
}