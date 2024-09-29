package com.arafat1419.collektr.presentation.ui.features.detail

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewEvent
import com.arafat1419.collektr.presentation.ui.viewmodel.IViewState

data class DetailViewState(
    val auction: Auction = Auction(),
    val chatBids: List<ChatBid> = listOf(),
    val highestBid: ChatBid = ChatBid(),
    val bidAmount: Long = 0,
    val bidError: String = "",
    val chatMessage: String = "",
    val countdown: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
) : IViewState

sealed class DetailViewEvent : IViewEvent {
    data class GetAuctionDetail(val auctionId: Int) : DetailViewEvent()
    data class SetFavoriteAuction(val auctionId: Int, val state: Boolean) : DetailViewEvent()
    data class GetAuctionBids(val auctionId: Int) : DetailViewEvent()
    data class GetHighestBid(val auctionId: Int) : DetailViewEvent()
    data class OnBidAmountChange(val amount: Long) : DetailViewEvent()
    data class OnChatMessageChange(val message: String) : DetailViewEvent()
    data class SendMessage(val auctionId: Int, val message: String) : DetailViewEvent()
    data class SendBid(val auctionId: Int) : DetailViewEvent()
    data class GetAuctionCountdown(val targetTime: Long) : DetailViewEvent()
    data object ShowPlaceBidBottomSheet : DetailViewEvent()
    data object HidePlaceBidBottomSheet : DetailViewEvent()
}