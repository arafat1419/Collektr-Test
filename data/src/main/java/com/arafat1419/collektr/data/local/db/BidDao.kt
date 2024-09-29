package com.arafat1419.collektr.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arafat1419.collektr.data.local.model.bid.BidEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface BidDao {
    @Query("SELECT * FROM auction_bid WHERE auction_id = :auctionId ORDER BY created_at DESC")
    fun getAuctionBids(auctionId: Int): Flow<List<BidEntity>>

    @Query("SELECT * FROM auction_bid WHERE auction_id = :auctionId ORDER BY bid_amount DESC LIMIT 1")
    fun getHighestBid(auctionId: Int): Flow<BidEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBid(bid: BidEntity)
}