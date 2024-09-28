package com.arafat1419.collektr.data.network.model.chat

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("auction_id")
    val auctionId: Int = 0,
    @SerializedName("user_name")
    val userName: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("created_at")
    val createdAt: Long = 0
)
