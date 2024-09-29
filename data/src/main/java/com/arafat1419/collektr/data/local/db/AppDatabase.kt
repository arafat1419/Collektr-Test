package com.arafat1419.collektr.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arafat1419.collektr.data.local.model.auction.AuctionEntity
import com.arafat1419.collektr.data.local.model.bid.BidEntity
import com.arafat1419.collektr.data.local.model.chat.ChatEntity

@Database(
    entities = [AuctionEntity::class, BidEntity::class, ChatEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun auctionDao(): AuctionDao
    abstract fun bidDao(): BidDao
    abstract fun chatDao(): ChatDao
}