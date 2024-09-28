package com.arafat1419.collektr.data.local.source.auction

import com.arafat1419.collektr.data.local.db.AuctionDao
import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import kotlinx.coroutines.flow.Flow

class AuctionLocalSourceImpl(private val auctionDao: AuctionDao) : AuctionLocalSource {
    override suspend fun getAuctionsByCategory(categoryId: Int): Flow<List<AuctionEntity>> =
        auctionDao.getAuctionsByCategory(categoryId)

    override suspend fun getAuctionDetails(auctionId: Int): Flow<AuctionEntity> =
        auctionDao.getAuctionDetails(auctionId)

    override suspend fun insertAuction(auction: AuctionEntity) =
        auctionDao.insertAuction(auction)
}