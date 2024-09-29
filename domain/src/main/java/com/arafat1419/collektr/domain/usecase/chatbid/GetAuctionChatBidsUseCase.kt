package com.arafat1419.collektr.domain.usecase.chatbid

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.repository.BidRepository
import com.arafat1419.collektr.domain.repository.ChatRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge

class GetAuctionChatBidsUseCase(
    private val bidRepository: BidRepository,
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(auctionId: Int): Flow<Resource<List<ChatBid>>> = flow {
        emit(Resource.Loading())
        try {
            val bidsFlow = bidRepository.getAuctionBids(auctionId)
            val chatsFlow = chatRepository.getAuctionChats(auctionId)

            val combinedFlow = merge(bidsFlow, chatsFlow)
            combinedFlow.collect { resource ->
                emit(
                    when (resource) {
                        is Resource.Success -> Resource.Success(resource.data)
                        is Resource.Error -> Resource.Error(resource.message)
                        is Resource.Loading -> Resource.Loading()
                    }
                )
            }
            emit(Resource.Success(emptyList()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}