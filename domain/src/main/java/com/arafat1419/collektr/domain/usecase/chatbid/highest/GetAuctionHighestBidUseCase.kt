package com.arafat1419.collektr.domain.usecase.chatbid.highest

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetAuctionHighestBidUseCase {
    suspend operator fun invoke(auctionId: Int): Flow<Resource<ChatBid>>
}