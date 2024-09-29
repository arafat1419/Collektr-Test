package com.arafat1419.collektr.presentation.ui.features.detail

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class DetailViewState(
    val auction: Auction = Auction(),
    val chatBids: List<ChatBid> = listOf(),
    val chatMessage: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState

sealed class DetailViewEvent : IViewEvent {
    data class GetAuctionDetail(val auctionId: Int) : DetailViewEvent()
    data class SetFavoriteAuction(val auctionId: Int, val state: Boolean) : DetailViewEvent()
    data class GetAuctionBids(val auctionId: Int) : DetailViewEvent()
    data class OnChatMessageChange(val message: String) : DetailViewEvent()
    data class SendMessage(val auctionId: Int, val message: String) : DetailViewEvent()
    data class SendBid(val auctionId: Int, val bidAmount: Long) : DetailViewEvent()
}