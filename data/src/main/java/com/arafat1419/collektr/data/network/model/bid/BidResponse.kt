package com.arafat1419.collektr.data.network.model.bid

import com.google.gson.annotations.SerializedName

data class BidResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("auction_id")
    val auctionId: Int = 0,
    @SerializedName("user_name")
    val userName: String = "",
    @SerializedName("bid_amount")
    val bidAmount: Int = 0,
    @SerializedName("created_at")
    val createdAt: Long = 0
)
