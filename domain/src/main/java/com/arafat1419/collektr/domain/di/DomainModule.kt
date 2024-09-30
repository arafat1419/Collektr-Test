package com.arafat1419.collektr.domain.di

import com.arafat1419.collektr.domain.repository.AuctionRepository
import com.arafat1419.collektr.domain.repository.BidRepository
import com.arafat1419.collektr.domain.repository.CatRepository
import com.arafat1419.collektr.domain.repository.ChatRepository
import com.arafat1419.collektr.domain.repository.FavoriteRepository
import com.arafat1419.collektr.domain.usecase.auction.categories.GetAuctionCategoriesUseCase
import com.arafat1419.collektr.domain.usecase.auction.categories.GetAuctionCategoriesUseCaseImpl
import com.arafat1419.collektr.domain.usecase.auction.countdown.GetAuctionCountdownUseCase
import com.arafat1419.collektr.domain.usecase.auction.countdown.GetAuctionCountdownUseCaseImpl
import com.arafat1419.collektr.domain.usecase.auction.detail.GetAuctionDetailsUseCase
import com.arafat1419.collektr.domain.usecase.auction.detail.GetAuctionDetailsUseCaseImpl
import com.arafat1419.collektr.domain.usecase.auction.list.GetAuctionsUseCase
import com.arafat1419.collektr.domain.usecase.auction.list.GetAuctionsUseCaseImpl
import com.arafat1419.collektr.domain.usecase.auction.live.GetLiveAuctionCountUseCase
import com.arafat1419.collektr.domain.usecase.auction.live.GetLiveAuctionCountUseCaseImpl
import com.arafat1419.collektr.domain.usecase.cat.GetCatFactUseCase
import com.arafat1419.collektr.domain.usecase.cat.GetCatFactUseCaseImpl
import com.arafat1419.collektr.domain.usecase.chatbid.highest.GetAuctionHighestBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.highest.GetAuctionHighestBidUseCaseImpl
import com.arafat1419.collektr.domain.usecase.chatbid.list.GetAuctionChatBidsUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.list.GetAuctionChatBidsUseCaseImpl
import com.arafat1419.collektr.domain.usecase.chatbid.send.SendAuctionBidUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.send.SendAuctionBidUseCaseImpl
import com.arafat1419.collektr.domain.usecase.chatbid.send.SendAuctionChatUseCase
import com.arafat1419.collektr.domain.usecase.chatbid.send.SendAuctionChatUseCaseImpl
import com.arafat1419.collektr.domain.usecase.favorite.GetFavoriteAuctionUseCase
import com.arafat1419.collektr.domain.usecase.favorite.GetFavoriteAuctionUseCaseImpl
import com.arafat1419.collektr.domain.usecase.favorite.SetFavoriteAuctionUseCase
import com.arafat1419.collektr.domain.usecase.favorite.SetFavoriteAuctionUseCaseImpl
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
        return GetCatFactUseCaseImpl(catRepository)
    }

    @Provides
    @Singleton
    fun provideAddFavoriteAuctionUseCase(favoriteRepository: FavoriteRepository): SetFavoriteAuctionUseCase {
        return SetFavoriteAuctionUseCaseImpl(favoriteRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteAuctionUseCase(favoriteRepository: FavoriteRepository): GetFavoriteAuctionUseCase {
        return GetFavoriteAuctionUseCaseImpl(favoriteRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionsUseCase(auctionRepository: AuctionRepository): GetAuctionsUseCase {
        return GetAuctionsUseCaseImpl(auctionRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionDetailsUseCase(auctionRepository: AuctionRepository): GetAuctionDetailsUseCase {
        return GetAuctionDetailsUseCaseImpl(auctionRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionCategoriesUseCase(auctionRepository: AuctionRepository): GetAuctionCategoriesUseCase {
        return GetAuctionCategoriesUseCaseImpl(auctionRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionBidsUseCase(
        bidRepository: BidRepository,
        chatRepository: ChatRepository
    ): GetAuctionChatBidsUseCase {
        return GetAuctionChatBidsUseCaseImpl(bidRepository, chatRepository)
    }

    @Provides
    @Singleton
    fun provideGetAuctionHighestBidUseCase(bidRepository: BidRepository): GetAuctionHighestBidUseCase {
        return GetAuctionHighestBidUseCaseImpl(bidRepository)
    }

    @Provides
    @Singleton
    fun provideSendAuctionBidUseCase(bidRepository: BidRepository): SendAuctionBidUseCase {
        return SendAuctionBidUseCaseImpl(bidRepository)
    }

    @Provides
    @Singleton
    fun provideSendAuctionChatUseCase(chatRepository: ChatRepository): SendAuctionChatUseCase {
        return SendAuctionChatUseCaseImpl(chatRepository)
    }

    @Provides
    @Singleton
    fun provideGetLiveAuctionCountUseCase(): GetLiveAuctionCountUseCase {
        return GetLiveAuctionCountUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideGetAuctionCountdownUseCase(): GetAuctionCountdownUseCase {
        return GetAuctionCountdownUseCaseImpl()
    }
}