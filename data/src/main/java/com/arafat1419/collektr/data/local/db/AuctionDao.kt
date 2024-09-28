package com.arafat1419.collektr.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arafat1419.collektr.data.local.model.AuctionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuctionDao {
    @Query("SELECT * FROM auctions WHERE category_id = :categoryId")
    fun getAuctionsByCategory(categoryId: Int): Flow<List<AuctionEntity>>

    @Query("SELECT * FROM auctions WHERE id = :auctionId")
    fun getAuctionDetails(auctionId: Int): Flow<AuctionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuction(auction: AuctionEntity)
}