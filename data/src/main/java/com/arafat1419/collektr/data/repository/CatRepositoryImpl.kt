package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.mapper.MapperResponseExtensions.toDomain
import com.arafat1419.collektr.data.network.source.CatNetworkSource
import com.arafat1419.collektr.domain.model.cat.CatFact
import com.arafat1419.collektr.domain.repository.CatRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

internal class CatRepositoryImpl(
    private val catNetworkSource: CatNetworkSource
) : CatRepository {
    override suspend fun getCatFact(): Flow<Resource<CatFact>> =
        NetworkBoundResource(
            shouldFetch = { true },
            createCall = { catNetworkSource.getCatFact() },
            processResponse = { response -> response.toDomain() }
        ).asFlow()
}