package com.arafat1419.collektr.presentation.ui.features.home

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class HomeViewState(
    val auctionCategories: List<AuctionCategory> = emptyList(),
    val selectedCategory: AuctionCategory = AuctionCategory(),
    val auctions: List<Auction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState


sealed class HomeViewEvent : IViewEvent {
    data object GetAuctionsCategories : HomeViewEvent()
    data class GetAuctions(val categoryId: Int) : HomeViewEvent()
    data class NavigateToDetails(val auction: Auction) : HomeViewEvent()
}