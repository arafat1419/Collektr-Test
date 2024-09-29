package com.arafat1419.collektr.data.local.model.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auction_chat")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "auction_id")
    val auctionId: Int = 0,

    @ColumnInfo(name = "user_name")
    val userName: String = "",

    @ColumnInfo(name = "message")
    val message: String = "",

    @ColumnInfo(name = "created_at")
    val createdAt: Long = 0
)
