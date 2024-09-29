package com.arafat1419.collektr.domain.repository

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getAuctionChats(auctionId: Int): Flow<Resource<List<ChatBid>>>
    suspend fun insertChat(chat: ChatBid)
}