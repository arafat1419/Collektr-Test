package com.arafat1419.collektr.domain.usecase.favorite

import com.arafat1419.collektr.domain.repository.FavoriteRepository

class SetFavoriteAuctionUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(auctionId: Int, isFavorite: Boolean) {
        favoriteRepository.setFavoriteAuction(auctionId, isFavorite)
    }
}