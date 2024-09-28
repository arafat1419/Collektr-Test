package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.domain.model.auction.AuctionCreator

object MapperEntityExtensions {
    fun AuctionEntity.toDomain(): Auction = Auction(
        id = id,
        creator = AuctionCreator(
            id = creatorId,
            name = creatorName,
            img = creatorImg
        ),
        category = AuctionCategory(
            id = categoryId,
            name = categoryName
        ),
        img = img,
        name = name,
        description = description,
        startBid = startBid,
        auctionEnd = auctionEnd,
        isLive = isLive,
        isFavorite = isFavorite
    )
}