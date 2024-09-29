package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import com.arafat1419.collektr.data.local.model.bid.BidEntity
import com.arafat1419.collektr.data.local.model.chat.ChatEntity
import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.chatbid.ChatBid

object MapperDomainExtensions {
    fun Auction.toBidEntity(): AuctionEntity = AuctionEntity(
        id = id,
        creatorId = creator.id,
        creatorName = creator.name,
        creatorImg = creator.img,
        categoryId = category.id,
        categoryName = category.name,
        img = img,
        name = name,
        description = description,
        startBid = startBid,
        auctionEnd = auctionEnd,
        isLive = isLive,
        isFavorite = isFavorite,
    )

    fun ChatBid.toBidEntity(): BidEntity = BidEntity(
        id = id,
        auctionId = auctionId,
        userName = userName,
        bidAmount = bidAmount,
        createdAt = createdAt
    )

    fun ChatBid.toChatEntity(): ChatEntity = ChatEntity(
        id = id,
        auctionId = auctionId,
        userName = userName,
        message = chatMessage,
        createdAt = createdAt
    )
}