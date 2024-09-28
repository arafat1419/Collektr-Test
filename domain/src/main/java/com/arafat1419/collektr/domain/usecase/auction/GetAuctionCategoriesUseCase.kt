package com.arafat1419.collektr.domain.usecase.auction

import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetAuctionCategoriesUseCase(
    private val auctionRepository: AuctionRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<AuctionCategory>>> {
        return auctionRepository.getAuctionCategories()
    }
}