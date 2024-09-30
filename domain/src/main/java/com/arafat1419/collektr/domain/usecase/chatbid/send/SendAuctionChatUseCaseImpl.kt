package com.arafat1419.collektr.domain.usecase.chatbid.send

import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.domain.repository.ChatRepository

class SendAuctionChatUseCaseImpl(
    private val chatRepository: ChatRepository
) : SendAuctionChatUseCase {
    override suspend operator fun invoke(chatBid: ChatBid) {
        chatRepository.insertChat(chatBid)
    }
}

