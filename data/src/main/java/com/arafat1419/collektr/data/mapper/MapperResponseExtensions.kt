package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.local.model.AuctionEntity
import com.arafat1419.collektr.data.network.model.auction.AuctionCategoryResponse
import com.arafat1419.collektr.data.network.model.auction.AuctionCreatorResponse
import com.arafat1419.collektr.data.network.model.auction.AuctionResponse
import com.arafat1419.collektr.data.network.model.cat.CatFactResponse
import com.arafat1419.collektr.domain.model.auction.Auction
import com.arafat1419.collektr.domain.model.auction.AuctionCategory
import com.arafat1419.collektr.domain.model.auction.AuctionCreator
import com.arafat1419.collektr.domain.model.cat.CatFact

object MapperResponseExtensions {
    fun CatFactResponse.toDomain(): CatFact = CatFact(
        fact = fact,
        length = length
    )

    fun AuctionResponse.toEntity(): AuctionEntity = AuctionEntity(
        id = id,
        creatorId = creator.id,
        creatorName = creator.name,
        creatorImg = creator.img,
        categoryId = category.id,
        categoryName = category.name,
        img = img,
        name = name,
        highestBid = highestBid,
        auctionEnd = auctionEnd,
        isLive = isLive,
    )

    fun AuctionResponse.toDomain(): Auction = Auction(
        id = id,
        creator = creator.toDomain(),
        category = category.toDomain(),
        img = img,
        name = name,
        highestBid = highestBid,
        auctionEnd = auctionEnd,
        isLive = isLive
    )

    fun AuctionCreatorResponse.toDomain(): AuctionCreator = AuctionCreator(
        id = id,
        name = name,
        img = img
    )

    fun AuctionCategoryResponse.toDomain(): AuctionCategory = AuctionCategory(
        id = id,
        name = name
    )
}