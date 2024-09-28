package com.arafat1419.collektr.data.network.source.cat

import com.arafat1419.collektr.data.network.api.ApiResponse
import com.arafat1419.collektr.data.network.api.CatService
import com.arafat1419.collektr.data.network.model.cat.CatFactResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

internal class CatNetworkSourceImpl(private val catService: CatService) : CatNetworkSource {
    override suspend fun getCatFact(): Flow<ApiResponse<CatFactResponse>> = networkHandler {
        catService.getCatFact()
    }

    private suspend fun <T> networkHandler(call: suspend () -> Response<T>): Flow<ApiResponse<T>> =
        flow {
            try {
                val response = call()
                if (response.isSuccessful) {
                    emit(ApiResponse.Success(response.body()!!))
                } else {
                    emit(ApiResponse.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}