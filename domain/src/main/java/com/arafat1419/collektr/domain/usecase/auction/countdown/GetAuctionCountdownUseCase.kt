package com.arafat1419.collektr.domain.usecase.auction.countdown

import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetAuctionCountdownUseCase {
    suspend operator fun invoke(targetTime: Long): Flow<Resource<String>>
}