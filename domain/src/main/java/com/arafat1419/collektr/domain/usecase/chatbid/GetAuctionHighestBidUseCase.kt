package com.arafat1419.collektr.domain.usecase.chatbid

import com.arafat1419.collektr.domain.repository.BidRepository

class GetAuctionHighestBidUseCase(
    private val bidRepository: BidRepository
) {
    suspend operator fun invoke(auctionId: Int) = bidRepository.getHighestBid(auctionId)
}