package com.arafat1419.collektr.domain.usecase.chatbid

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.repository.ChatRepository

class SendAuctionChatUseCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(chatBid: ChatBid) {
        chatRepository.insertChat(chatBid)
    }

}