package com.arafat1419.collektr.data.local.source.bid

import com.arafat1419.collektr.data.local.db.BidDao
import com.arafat1419.collektr.data.local.model.bid.BidEntity
import kotlinx.coroutines.flow.Flow

internal class BidLocalSourceImpl(private val bidDao: BidDao) : BidLocalSource {
    override suspend fun getAuctionBids(auctionId: Int): Flow<List<BidEntity>> =
        bidDao.getAuctionBids(auctionId)

    override suspend fun insertBid(bid: BidEntity) =
        bidDao.insertBid(bid)
}