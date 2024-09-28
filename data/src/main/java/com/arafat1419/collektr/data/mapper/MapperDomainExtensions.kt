package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.local.model.AuctionEntity
import com.arafat1419.collektr.domain.model.auction.Auction

object MapperDomainExtensions {
    fun Auction.toEntity(): AuctionEntity = AuctionEntity(
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
}