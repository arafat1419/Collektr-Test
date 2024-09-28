package com.arafat1419.collektr.data.local.source.auction

import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import kotlinx.coroutines.flow.Flow

interface AuctionLocalSource {
    suspend fun getAuctionsByCategory(categoryId: Int): Flow<List<AuctionEntity>>
    suspend fun getAuctionDetails(auctionId: Int): Flow<AuctionEntity>
    suspend fun insertAuction(auction: AuctionEntity)
}