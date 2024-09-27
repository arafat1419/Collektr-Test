package com.arafat1419.collektr.data.repository

import com.arafat1419.collektr.data.network.api.ApiResponse
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

internal class NetworkBoundResource<ResultType, RequestType>(
    private val shouldFetch: (data: ResultType?) -> Boolean,
    private val createCall: suspend () -> Flow<ApiResponse<RequestType>> = { flowOf() },
    private val processResponse: suspend (RequestType) -> ResultType? = { null },
    private val loadFromDB: suspend () -> Flow<ResultType>? = { null },
    private val saveCallResult: suspend (RequestType) -> Unit = {},
    private val onFetchFailed: suspend () -> Unit = {}
) {

    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        try {
            val dbSource = loadFromDB()?.first()

            if (shouldFetch(dbSource)) {
                createCall().collect { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Success -> {
                            saveCallResult(apiResponse.data)
                            emit(Resource.Success(processResponse(apiResponse.data)))
                        }

                        is ApiResponse.Error -> {
                            onFetchFailed()
                            emit(Resource.Error(apiResponse.error))
                        }
                    }
                }
            } else {
                loadFromDB()?.let { dbSource ->
                    emitAll(dbSource.map { Resource.Success(it) })
                }

            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}