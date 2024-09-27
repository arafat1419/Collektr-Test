package com.arafat1419.collektr.domain.di

import com.arafat1419.collektr.domain.repository.CatRepository
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.usecase.cat.GetCatFactUseCase
import com.arafat1419.collektr.domain.usecase.favorite.AddFavoriteAuctionUseCase
import com.arafat1419.collektr.domain.usecase.favorite.GetFavoriteAuctionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetCatFactUseCase(catRepository: CatRepository): GetCatFactUseCase {
        return GetCatFactUseCase(catRepository)
    }

    @Provides
    @Singleton
    fun provideAddFavoriteAuctionUseCase(favoriteRepository: FavoriteRepository): AddFavoriteAuctionUseCase {
        return AddFavoriteAuctionUseCase(favoriteRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteAuctionUseCase(favoriteRepository: FavoriteRepository): GetFavoriteAuctionUseCase {
        return GetFavoriteAuctionUseCase(favoriteRepository)
    }
}