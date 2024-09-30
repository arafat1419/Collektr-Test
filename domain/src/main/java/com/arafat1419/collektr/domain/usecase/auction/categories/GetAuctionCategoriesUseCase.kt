package com.arafat1419.collektr.domain.usecase.auction.categories

import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetAuctionCategoriesUseCase {
    suspend operator fun invoke(): Flow<Resource<List<AuctionCategory>>>
}