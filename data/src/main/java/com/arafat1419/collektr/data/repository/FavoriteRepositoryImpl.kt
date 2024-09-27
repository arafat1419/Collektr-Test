package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.local.model.AuctionEntity
import com.arafat1419.collektr.data.local.source.FavoriteLocalSource
import com.arafat1419.collektr.data.mapper.MapperDomainExtensions.toEntity
import com.arafat1419.collektr.data.mapper.MapperEntityExtensions.toDomain
import com.arafat1419.collektr.domain.model.favorite.FavoriteAuction
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteRepositoryImpl(
    private val favoriteLocalSource: FavoriteLocalSource
) : FavoriteRepository {
    override suspend fun getFavoriteAuctions(): Flow<Resource<List<FavoriteAuction>>> =
        NetworkBoundResource<List<FavoriteAuction>, List<AuctionEntity>>(
            shouldFetch = { false },
            loadFromDB = {
                favoriteLocalSource.getAuctions().map {
                    it.map { auctionEntity -> auctionEntity.toDomain() }
                }
            },
        ).asFlow()


    override suspend fun addFavoriteAuction(favoriteAuction: FavoriteAuction) {
        favoriteLocalSource.insertAuction(favoriteAuction.toEntity())

    }

}