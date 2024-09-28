package com.arafat1419.collektr.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arafat1419.collektr.data.local.model.AuctionEntity

@Database(entities = [AuctionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun auctionDao(): AuctionDao
}