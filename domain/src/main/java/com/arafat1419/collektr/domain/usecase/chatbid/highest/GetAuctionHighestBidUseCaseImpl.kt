package com.arafat1419.collektr.domain.usecase.chatbid.highest

import com.arafat1419.collektr.domain.repository.BidRepository

class GetAuctionHighestBidUseCaseImpl(
    private val bidRepository: BidRepository
) : GetAuctionHighestBidUseCase {
    override suspend operator fun invoke(auctionId: Int) = bidRepository.getHighestBid(auctionId)
}

