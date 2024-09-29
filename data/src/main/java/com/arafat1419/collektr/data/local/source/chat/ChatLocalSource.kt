package com.arafat1419.collektr.data.local.source.chat

import com.arafat1419.collektr.data.local.model.chat.ChatEntity
import kotlinx.coroutines.flow.Flow

interface ChatLocalSource {
    suspend fun getAuctionChats(auctionId: Int): Flow<List<ChatEntity>>
    suspend fun insertChat(chat: ChatEntity)
}
