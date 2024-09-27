package com.arafat1419.collektr.data.di

import com.arafat1419.collektr.data.BuildConfig
import com.arafat1419.collektr.data.network.api.CatService
import com.arafat1419.collektr.data.network.source.CatNetworkSource
import com.arafat1419.collektr.data.network.source.CatNetworkSourceImpl
import com.arafat1419.collektr.data.repository.CatRepositoryImpl
import com.arafat1419.collektr.domain.repository.CatRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideCatService(
        gsonConverterFactory: GsonConverterFactory,
    ): CatService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(CatService::class.java)
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
}