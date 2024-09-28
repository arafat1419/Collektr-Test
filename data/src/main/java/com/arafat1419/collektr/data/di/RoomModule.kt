package com.arafat1419.collektr.data.di

import android.content.Context
import androidx.room.Room
import com.arafat1419.collektr.data.local.db.AppDatabase
import com.arafat1419.collektr.data.local.db.AuctionDao
import com.arafat1419.collektr.data.local.db.FavoriteDao
import com.arafat1419.collektr.data.local.source.auction.AuctionLocalSource
import com.arafat1419.collektr.data.local.source.auction.AuctionLocalSourceImpl
import com.arafat1419.collektr.data.local.source.favorite.FavoriteLocalSource
import com.arafat1419.collektr.data.local.source.favorite.FavoriteLocalSourceImpl
import com.arafat1419.collektr.data.network.source.auction.AuctionMockSource
import com.arafat1419.collektr.data.repository.AuctionRepositoryImpl
import com.arafat1419.collektr.data.repository.FavoriteRepositoryImpl
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteLocalSource(favoriteDao: FavoriteDao): FavoriteLocalSource {
        return FavoriteLocalSourceImpl(favoriteDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteLocalSource: FavoriteLocalSource): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteLocalSource)
    }

    @Provides
    @Singleton
    fun provideAuctionDao(appDatabase: AppDatabase): AuctionDao {
        return appDatabase.auctionDao()
    }

    @Provides
    @Singleton
    fun provideAuctionLocalSource(auctionDao: AuctionDao): AuctionLocalSource {
        return AuctionLocalSourceImpl(auctionDao)
    }

    @Provides
    @Singleton
    fun provideAuctionRepository(
        auctionMockSource: AuctionMockSource,
        auctionLocalSource: AuctionLocalSource
    ): AuctionRepository {
        return AuctionRepositoryImpl(auctionMockSource, auctionLocalSource)
    }
}