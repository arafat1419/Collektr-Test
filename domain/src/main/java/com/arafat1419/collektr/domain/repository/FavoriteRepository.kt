package com.arafat1419.collektr.domain.repository

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getFavoriteAuctions(): Flow<Resource<List<Auction>>>
    suspend fun setFavoriteAuction(auctionId: Int, isFavorite: Boolean)
}