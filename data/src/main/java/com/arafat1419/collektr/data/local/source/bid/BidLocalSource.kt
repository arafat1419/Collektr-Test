package com.arafat1419.collektr.data.local.source.bid

import com.arafat1419.collektr.data.local.model.bid.BidEntity
import kotlinx.coroutines.flow.Flow

internal interface BidLocalSource {
    suspend fun getAuctionBids(auctionId: Int): Flow<List<BidEntity>>
    suspend fun getHighestBid(auctionId: Int): Flow<BidEntity?>
    suspend fun insertBid(bid: BidEntity)
}
