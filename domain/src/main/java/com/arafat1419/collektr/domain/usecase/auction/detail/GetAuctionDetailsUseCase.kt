package com.arafat1419.collektr.domain.usecase.auction.detail

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetAuctionDetailsUseCase {
    suspend operator fun invoke(auctionId: Int): Flow<Resource<Auction>>
}