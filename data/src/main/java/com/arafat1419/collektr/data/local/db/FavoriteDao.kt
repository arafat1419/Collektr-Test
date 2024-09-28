package com.arafat1419.collektr.data.local.db

import androidx.room.Dao
import androidx.room.Query
import com.arafat1419.collektr.data.local.model.AuctionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM auctions WHERE is_favorite = 1")
    fun getFavoriteAuctions(): Flow<List<AuctionEntity>>

    @Query("UPDATE auctions SET is_favorite = :isFavorite WHERE id = :auctionId")
    suspend fun setFavoriteAuction(auctionId: Int, isFavorite: Boolean)
}