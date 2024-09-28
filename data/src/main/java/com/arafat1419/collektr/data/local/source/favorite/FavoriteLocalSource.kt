package com.arafat1419.collektr.data.local.source.favorite

import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalSource {
    fun getAuctions(): Flow<List<AuctionEntity>>
    suspend fun setFavoriteAuction(auctionId: Int, isFavorite: Boolean)
}