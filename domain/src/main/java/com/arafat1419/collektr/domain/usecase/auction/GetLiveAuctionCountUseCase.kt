package com.arafat1419.collektr.domain.usecase.auction

import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLiveAuctionCountUseCase {
    suspend operator fun invoke(auctionId: Int): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            while (true) {
                delay(3000)
                val createRandomNumber = (0..1000).random()
                emit(Resource.Success(createRandomNumber))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}