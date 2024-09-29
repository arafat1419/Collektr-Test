package com.arafat1419.collektr.domain.repository

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface BidRepository {
    suspend fun getAuctionBids(auctionId: Int): Flow<Resource<List<ChatBid>>>
    suspend fun getHighestBid(auctionId: Int): Flow<Resource<ChatBid>>
    suspend fun insertBid(chatBid: ChatBid)
}