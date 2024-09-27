package com.arafat1419.collektr.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arafat1419.collektr.data.local.model.AuctionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM auctions")
    fun getFavoriteAuctions(): Flow<List<AuctionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAuction(auction: AuctionEntity)

    @Delete(entity = AuctionEntity::class)
    suspend fun deleteFavoriteAuction(auction: AuctionEntity)
}