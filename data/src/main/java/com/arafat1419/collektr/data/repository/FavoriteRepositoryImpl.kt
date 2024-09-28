package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import com.arafat1419.collektr.data.local.source.favorite.FavoriteLocalSource
import com.arafat1419.collektr.data.mapper.MapperEntityExtensions.toDomain
import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteRepositoryImpl(
    private val favoriteLocalSource: FavoriteLocalSource
) : FavoriteRepository {
    override suspend fun getFavoriteAuctions(): Flow<Resource<List<Auction>>> =
        NetworkBoundResource<List<Auction>, List<AuctionEntity>>(
            shouldFetch = { false },
            loadFromDB = {
                favoriteLocalSource.getAuctions().map {
                    it.map { auctionEntity -> auctionEntity.toDomain() }
                }
            },
        ).asFlow()

    override suspend fun setFavoriteAuction(auctionId: Int, isFavorite: Boolean) {
        favoriteLocalSource.setFavoriteAuction(auctionId, isFavorite)
    }
}