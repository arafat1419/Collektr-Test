package com.arafat1419.collektr.presentation.ui.features.live

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class LiveViewState(
    val auction: Auction = Auction(),
    val liveCount: Int = 0,
    val chatBids: List<ChatBid> = listOf(),
    val highestBid: ChatBid = ChatBid(),
    val chatMessage: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState

sealed class LiveViewEvent : IViewEvent {
    data class GetAuctionDetail(val auctionId: Int) : LiveViewEvent()
    data class GetLiveAuctionCount(val auctionId: Int) : LiveViewEvent()
    data class GetHighestBid(val auctionId: Int) : LiveViewEvent()
    data class SetFavoriteAuction(val auctionId: Int, val state: Boolean) : LiveViewEvent()
    data class GetAuctionBids(val auctionId: Int) : LiveViewEvent()
    data class OnChatMessageChange(val message: String) : LiveViewEvent()
    data class SendMessage(val auctionId: Int, val message: String) : LiveViewEvent()
    data class SendBid(val auctionId: Int, val bidAmount: Long) : LiveViewEvent()
}