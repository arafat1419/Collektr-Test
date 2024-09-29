package com.arafat1419.collektr.domain.model.chatbid

data class ChatBid(
    val id: Int = 0,
    val auctionId: Int = 0,
    val userName: String = "",
    val bidAmount: Long = 0,
    val chatMessage: String = "",
    val isBid: Boolean = true,
    val createdAt: Long = 0
)
