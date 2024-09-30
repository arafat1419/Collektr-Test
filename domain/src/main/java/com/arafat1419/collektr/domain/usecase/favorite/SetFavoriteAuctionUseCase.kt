package com.arafat1419.collektr.domain.usecase.favorite

interface SetFavoriteAuctionUseCase {
    suspend operator fun invoke(auctionId: Int, isFavorite: Boolean)
}