package com.arafat1419.collektr.domain.usecase.auction

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetAuctionsUseCase(
    private val auctionRepository: AuctionRepository
) {
    suspend operator fun invoke(categoryId: Int): Flow<Resource<List<Auction>>> {
        return auctionRepository.getAuctions(categoryId)
    }
}

