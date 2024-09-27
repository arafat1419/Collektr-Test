package com.arafat1419.collektr.domain.repository

import com.arafat1419.collektr.domain.model.favorite.FavoriteAuction
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getFavoriteAuctions(): Flow<Resource<List<FavoriteAuction>>>
    suspend fun addFavoriteAuction(favoriteAuction: FavoriteAuction)
}