package com.arafat1419.collektr.domain.di

import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.repository.BidRepository
import com.arafat1419.collektr.domain.repository.CatRepository
import com.arafat1419.collektr.domain.repository.ChatRepository
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionCategoriesUseCase
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionDetailsUseCase
import com.arafat1419.collektr.domain.usecase.auction.GetAuctionsUseCase
import com.arafat1419.collektr.domain.usecase.auction.GetLiveAuctionCountUseCase
import com.arafat1419.collektr.domain.usecase.cat.GetCatFactUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.GetAuctionChatBidsUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.GetAuctionHighestBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.SendAuctionBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.SendAuctionChatUseCase
import com.arafat1419.collektr.domain.usecase.favorite.GetFavoriteAuctionUseCase
import com.arafat1419.collektr.domain.usecase.favorite.SetFavoriteAuctionUseCase
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
    fun provideAddFavoriteAuctionUseCase(favoriteRepository: FavoriteRepository): SetFavoriteAuctionUseCase {
        return SetFavoriteAuctionUseCase(favoriteRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteAuctionUseCase(favoriteRepository: FavoriteRepository): GetFavoriteAuctionUseCase {
        return GetFavoriteAuctionUseCase(favoriteRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionsUseCase(auctionRepository: AuctionRepository): GetAuctionsUseCase {
        return GetAuctionsUseCase(auctionRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionDetailsUseCase(auctionRepository: AuctionRepository): GetAuctionDetailsUseCase {
        return GetAuctionDetailsUseCase(auctionRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionCategoriesUseCase(auctionRepository: AuctionRepository): GetAuctionCategoriesUseCase {
        return GetAuctionCategoriesUseCase(auctionRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionBidsUseCase(
        bidRepository: BidRepository,
        chatRepository: ChatRepository
    ): GetAuctionChatBidsUseCase {
        return GetAuctionChatBidsUseCase(bidRepository, chatRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionHighestBidUseCase(bidRepository: BidRepository): GetAuctionHighestBidUseCase {
        return GetAuctionHighestBidUseCase(bidRepository)
    }

    @Provides
    @Singleton
    fun provideSendAuctionBidUseCase(bidRepository: BidRepository): SendAuctionBidUseCase {
        return SendAuctionBidUseCase(bidRepository)
    }

    @Provides
    @Singleton
    fun provideSendAuctionChatUseCase(chatRepository: ChatRepository): SendAuctionChatUseCase {
        return SendAuctionChatUseCase(chatRepository)
    }

    @Provides
    @Singleton
    fun provideGetLiveAuctionCountUseCase(): GetLiveAuctionCountUseCase {
        return GetLiveAuctionCountUseCase()
    }
}