package com.arafat1419.collektr.data.di

import android.content.Context
import androidx.room.Room
import com.arafat1419.collektr.data.local.db.AppDatabase
import com.arafat1419.collektr.data.local.db.FavoriteDao
import com.arafat1419.collektr.data.local.source.FavoriteLocalSource
import com.arafat1419.collektr.data.local.source.FavoriteLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

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
}