package com.arafat1419.collektr.domain.usecase.auction.detail

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetAuctionDetailsUseCaseImpl(
    private val auctionRepository: AuctionRepository
) : GetAuctionDetailsUseCase {
    override suspend operator fun invoke(auctionId: Int): Flow<Resource<Auction>> {
        return auctionRepository.getAuctionDetails(auctionId)
    }
}

