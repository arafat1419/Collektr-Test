package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.local.model.AuctionEntity
import com.arafat1419.collektr.domain.model.favorite.FavoriteAuction

object MapperDomainExtensions {
    fun FavoriteAuction.toEntity(): AuctionEntity = AuctionEntity(
        id = id,
        title = title
    )
}