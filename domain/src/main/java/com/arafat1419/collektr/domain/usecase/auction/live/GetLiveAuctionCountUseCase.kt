package com.arafat1419.collektr.domain.usecase.auction.live

import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetLiveAuctionCountUseCase {
    suspend operator fun invoke(auctionId: Int): Flow<Resource<Int>>
}