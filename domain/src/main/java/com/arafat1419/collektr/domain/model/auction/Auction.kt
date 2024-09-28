package com.arafat1419.collektr.domain.model.auction


data class Auction(
    val id: Int = 0,
    val creator: AuctionCreator = AuctionCreator(),
    val category: AuctionCategory = AuctionCategory(),
    val img: String = "",
    val name: String = "",
    val highestBid: Long = 0,
    val auctionEnd: Long = 0,
    val isLive: Boolean = false,
    val isFavorite: Boolean = false
)

