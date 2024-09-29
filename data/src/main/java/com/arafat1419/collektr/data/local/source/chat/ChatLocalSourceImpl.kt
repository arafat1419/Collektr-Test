package com.arafat1419.collektr.data.local.source.chat

import com.arafat1419.collektr.data.local.db.ChatDao
import com.arafat1419.collektr.data.local.model.chat.ChatEntity
import kotlinx.coroutines.flow.Flow

internal class ChatLocalSourceImpl(private val chatDao: ChatDao) : ChatLocalSource {
    override suspend fun getAuctionChats(auctionId: Int): Flow<List<ChatEntity>> =
        chatDao.getAuctionChats(auctionId)

    override suspend fun insertChat(chat: ChatEntity) =
        chatDao.insertChat(chat)
}