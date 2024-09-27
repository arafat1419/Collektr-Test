package com.arafat1419.collektr.domain.usecase.favorite

import com.arafat1419.collektr.domain.model.favorite.FavoriteAuction
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetFavoriteAuctionUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<FavoriteAuction>>> {
        return favoriteRepository.getFavoriteAuctions()
    }

}