package com.arafat1419.collektr.domain.usecase.auction.list

import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetAuctionsUseCase {
    suspend operator fun invoke(categoryId: Int): Flow<Resource<List<Auction>>>
}