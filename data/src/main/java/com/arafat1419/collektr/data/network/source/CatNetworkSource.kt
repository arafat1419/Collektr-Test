package com.arafat1419.collektr.data.network.source

import com.arafat1419.collektr.data.network.api.ApiResponse
import com.arafat1419.collektr.data.network.model.cat.CatFactResponse
import kotlinx.coroutines.flow.Flow

internal interface CatNetworkSource {
    suspend fun getCatFact(): Flow<ApiResponse<CatFactResponse>>
}
