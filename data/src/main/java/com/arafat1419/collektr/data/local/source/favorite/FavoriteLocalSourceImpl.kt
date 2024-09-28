package com.arafat1419.collektr.data.local.source.favorite

import com.arafat1419.collektr.data.local.db.FavoriteDao
import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import kotlinx.coroutines.flow.Flow

class FavoriteLocalSourceImpl(private val favoriteDao: FavoriteDao) : FavoriteLocalSource {
    override fun getAuctions(): Flow<List<AuctionEntity>> = favoriteDao.getFavoriteAuctions()
    override suspend fun setFavoriteAuction(auctionId: Int, isFavorite: Boolean) {
        favoriteDao.setFavoriteAuction(auctionId, isFavorite)
    }
}