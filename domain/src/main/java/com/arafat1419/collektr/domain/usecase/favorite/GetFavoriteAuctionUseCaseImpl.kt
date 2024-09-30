package com.arafat1419.collektr.domain.usecase.favorite

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetFavoriteAuctionUseCaseImpl(
    private val favoriteRepository: FavoriteRepository
) : GetFavoriteAuctionUseCase {
    override suspend operator fun invoke(): Flow<Resource<List<Auction>>> {
        return favoriteRepository.getFavoriteAuctions()
    }

}

