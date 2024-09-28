package com.arafat1419.collektr.presentation.ui.features.detail

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class DetailViewState(
    val auction: Auction = Auction(),
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState

sealed class DetailViewEvent : IViewEvent {
    data class GetAuctionDetail(val auctionId: Int) : DetailViewEvent()
    data class SetFavoriteAuction(val auctionId: Int, val state: Boolean) : DetailViewEvent()
}