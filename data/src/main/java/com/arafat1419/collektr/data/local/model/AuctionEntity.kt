package com.arafat1419.collektr.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auctions")
data class AuctionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "creator_id")
    val creatorId: Int,

    @ColumnInfo(name = "creator_name")
    val creatorName: String,

    @ColumnInfo(name = "creator_img")
    val creatorImg: String,

    @ColumnInfo(name = "category_id")
    val categoryId: Int,

    @ColumnInfo(name = "category_name")
    val categoryName: String,

    @ColumnInfo(name = "img")
    val img: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "start_bid")
    val startBid: Long,

    @ColumnInfo(name = "auction_end")
    val auctionEnd: Long,

    @ColumnInfo(name = "is_live")
    val isLive: Boolean,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)