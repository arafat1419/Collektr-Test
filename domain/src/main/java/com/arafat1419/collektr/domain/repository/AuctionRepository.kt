package com.arafat1419.collektr.domain.repository

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface AuctionRepository {
    suspend fun getAuctions(categoryId: Int): Flow<Resource<List<Auction>>>
    suspend fun getAuctionDetails(auctionId: Int): Flow<Resource<Auction>>
    suspend fun getAuctionCategories(): Flow<Resource<List<AuctionCategory>>>
}