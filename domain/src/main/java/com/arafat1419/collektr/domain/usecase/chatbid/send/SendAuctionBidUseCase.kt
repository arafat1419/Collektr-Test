package com.arafat1419.collektr.domain.usecase.chatbid.send

import com.arafat1419.collektr.domain.model.chatbid.ChatBid

interface SendAuctionBidUseCase {
    suspend operator fun invoke(chatBid: ChatBid, highestBid: Long): String
}