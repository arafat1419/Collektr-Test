package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.local.model.AuctionEntity
import com.arafat1419.collektr.domain.model.favorite.FavoriteAuction

object MapperEntityExtensions {
    fun AuctionEntity.toDomain(): FavoriteAuction = FavoriteAuction(
        id = id,
        title = title
    )
}