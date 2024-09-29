package com.arafat1419.collektr.domain.usecase.chatbid

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.repository.BidRepository

class SendAuctionBidUseCase(
    private val bidRepository: BidRepository
) {
    suspend operator fun invoke(chatBid: ChatBid, highestBid: Long): String =
        if (chatBid.bidAmount > highestBid) {
            bidRepository.insertBid(chatBid)
            ""
        } else {
            "Bid must be higher that current highest bid"
        }

}