package com.arafat1419.collektr.data.di

import android.content.Context
import com.arafat1419.collektr.data.BuildConfig
import com.arafat1419.collektr.data.network.api.CatService
import com.arafat1419.collektr.data.network.source.auction.AuctionMockSource
import com.arafat1419.collektr.data.network.source.auction.AuctionMockSourceImpl
import com.arafat1419.collektr.data.network.source.cat.CatNetworkSource
import com.arafat1419.collektr.data.network.source.cat.CatNetworkSourceImpl
import com.arafat1419.collektr.data.repository.CatRepositoryImpl
import com.arafat1419.collektr.domain.repository.CatRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideCatService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): CatService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(CatService::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    )
                }
            )
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    fun provideCatNetworkSource(catService: CatService): CatNetworkSource {
        return CatNetworkSourceImpl(catService)
    }

    @Provides
    @Singleton
    fun provideCatRepository(catNetworkSource: CatNetworkSource): CatRepository {
        return CatRepositoryImpl(catNetworkSource)
    }

    @Provides
    @Singleton
    fun provideAuctionMockSource(
        @ApplicationContext context: Context,
        gson: Gson
    ): AuctionMockSource {
        return AuctionMockSourceImpl(context, gson)
    }
}