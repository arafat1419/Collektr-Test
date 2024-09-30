package com.arafat1419.collektr.domain.usecase.favorite

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavoriteAuctionUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Auction>>>
}