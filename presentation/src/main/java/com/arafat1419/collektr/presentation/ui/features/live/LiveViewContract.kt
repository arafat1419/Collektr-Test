package com.arafat1419.collektr.presentation.ui.features.live

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class LiveViewState(
    val auction: Auction = Auction(),
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState

sealed class LiveViewEvent : IViewEvent {
    data class GetAuctionDetail(val auctionId: Int) : LiveViewEvent()
    data class SetFavoriteAuction(val auctionId: Int, val state: Boolean) : LiveViewEvent()
}