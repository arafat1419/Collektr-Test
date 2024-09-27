package com.arafat1419.collektr.domain.usecase.favorite

import com.arafat1419.collektr.domain.model.favorite.FavoriteAuction
import com.arafat1419.collektr.domain.repository.FavoriteRepository

class AddFavoriteAuctionUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(favoriteAuction: FavoriteAuction) {
        favoriteRepository.addFavoriteAuction(favoriteAuction)
    }
}