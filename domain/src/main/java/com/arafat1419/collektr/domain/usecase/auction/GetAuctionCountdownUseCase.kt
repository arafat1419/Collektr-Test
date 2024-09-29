package com.arafat1419.collektr.domain.usecase.auction

import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

class GetAuctionCountdownUseCase {
    suspend operator fun invoke(targetTime: Long): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            while (true) {
                val currentTime = System.currentTimeMillis() / 1000
                val remainingTime = targetTime - currentTime
                if (remainingTime <= 0) {
                    emit(Resource.Success("00:00:00"))
                    break
                } else {
                    emit(Resource.Success(formatTime(remainingTime)))
                }
                delay(1000L)
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }

    private fun formatTime(timeInSeconds: Long): String {
        val hours = TimeUnit.SECONDS.toHours(timeInSeconds)
        val minutes = TimeUnit.SECONDS.toMinutes(timeInSeconds) % 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}