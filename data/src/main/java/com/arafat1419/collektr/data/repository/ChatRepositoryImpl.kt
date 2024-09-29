package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.local.model.chat.ChatEntity
import com.arafat1419.collektr.data.local.source.chat.ChatLocalSource
import com.arafat1419.collektr.data.mapper.MapperDomainExtensions.toChatEntity
import com.arafat1419.collektr.data.mapper.MapperEntityExtensions.toDomain
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.repository.ChatRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ChatRepositoryImpl(private val chatLocalSource: ChatLocalSource) : ChatRepository {
    override suspend fun getAuctionChats(auctionId: Int): Flow<Resource<List<ChatBid>>> =
        NetworkBoundResource<List<ChatBid>, List<ChatEntity>>(
            shouldFetch = { false },
            loadFromDB = {
                chatLocalSource.getAuctionChats(auctionId).map {
                    it.map { chatEntity -> chatEntity.toDomain() }
                }
            }
        ).asFlow()

    override suspend fun insertChat(chat: ChatBid) {
        chatLocalSource.insertChat(chat.toChatEntity())
    }
}