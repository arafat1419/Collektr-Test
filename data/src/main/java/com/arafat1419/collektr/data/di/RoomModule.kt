package com.arafat1419.collektr.data.di

import android.content.Context
import androidx.room.Room
import com.arafat1419.collektr.data.local.db.AppDatabase
import com.arafat1419.collektr.data.local.db.AuctionDao
import com.arafat1419.collektr.data.local.db.BidDao
import com.arafat1419.collektr.data.local.db.ChatDao
import com.arafat1419.collektr.data.local.db.FavoriteDao
import com.arafat1419.collektr.data.local.source.auction.AuctionLocalSource
import com.arafat1419.collektr.data.local.source.auction.AuctionLocalSourceImpl
import com.arafat1419.collektr.data.local.source.bid.BidLocalSource
import com.arafat1419.collektr.data.local.source.bid.BidLocalSourceImpl
import com.arafat1419.collektr.data.local.source.chat.ChatLocalSource
import com.arafat1419.collektr.data.local.source.chat.ChatLocalSourceImpl
import com.arafat1419.collektr.data.local.source.favorite.FavoriteLocalSource
import com.arafat1419.collektr.data.local.source.favorite.FavoriteLocalSourceImpl
import com.arafat1419.collektr.data.network.source.auction.AuctionMockSource
import com.arafat1419.collektr.data.repository.AuctionRepositoryImpl
import com.arafat1419.collektr.data.repository.BidRepositoryImpl
import com.arafat1419.collektr.data.repository.ChatRepositoryImpl
import com.arafat1419.collektr.data.repository.FavoriteRepositoryImpl
import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.repository.BidRepository
import com.arafat1419.collektr.domain.repository.ChatRepository
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

    @Provides
    @Singleton
    fun provideBidDao(appDatabase: AppDatabase): BidDao {
        return appDatabase.bidDao()
    }

    @Provides
    @Singleton
    fun provideBidRepository(bidLocalSource: BidLocalSource): BidRepository {
        return BidRepositoryImpl(bidLocalSource)
    }

    @Provides
    @Singleton
    fun provideBidLocalSource(bidDao: BidDao): BidLocalSource {
        return BidLocalSourceImpl(bidDao)
    }

    @Provides
    @Singleton
    fun provideChatDao(appDatabase: AppDatabase): ChatDao {
        return appDatabase.chatDao()
    }

    @Provides
    @Singleton
    fun provideChatLocalSource(chatDao: ChatDao): ChatLocalSource {
        return ChatLocalSourceImpl(chatDao)
    }

    @Provides
    @Singleton
    fun provideChatRepository(chatLocalSource: ChatLocalSource): ChatRepository {
        return ChatRepositoryImpl(chatLocalSource)
    }
}