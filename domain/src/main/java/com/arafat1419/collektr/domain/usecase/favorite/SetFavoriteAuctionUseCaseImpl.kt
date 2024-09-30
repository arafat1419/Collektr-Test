package com.arafat1419.collektr.domain.usecase.favorite

import com.arafat1419.collektr.domain.repository.FavoriteRepository

class SetFavoriteAuctionUseCaseImpl(
    private val favoriteRepository: FavoriteRepository
) : SetFavoriteAuctionUseCase {
    override suspend operator fun invoke(auctionId: Int, isFavorite: Boolean) {
        favoriteRepository.setFavoriteAuction(auctionId, isFavorite)
    }
}

