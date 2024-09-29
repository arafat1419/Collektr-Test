package com.arafat1419.collektr.data.local.model.bid

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auction_bid")
data class BidEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "auction_id")
    val auctionId: Int = 0,

    @ColumnInfo(name = "user_name")
    val userName: String = "",

    @ColumnInfo(name = "bid_amount")
    val bidAmount: Long = 0,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = 0
)
