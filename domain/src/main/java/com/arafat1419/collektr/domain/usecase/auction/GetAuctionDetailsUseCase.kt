package com.arafat1419.collektr.domain.usecase.auction

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetAuctionDetailsUseCase(
    private val auctionRepository: AuctionRepository
) {
    suspend operator fun invoke(auctionId: Int): Flow<Resource<Auction>> {
        return auctionRepository.getAuctionDetails(auctionId)
    }
}