package com.arafat1419.collektr.data.network.model.auction

import com.google.gson.annotations.SerializedName

data class AuctionResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("creator")
    val creator: AuctionCreatorResponse = AuctionCreatorResponse(),
    @SerializedName("category")
    val category: AuctionCategoryResponse = AuctionCategoryResponse(),
    @SerializedName("img")
    val img: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("highest_bid")
    val highestBid: Int = 0,
    @SerializedName("auction_end")
    val auctionEnd: Long = 0,
    @SerializedName("is_live")
    val isLive: Boolean = false
)

