package com.arafat1419.collektr.domain.usecase.cat

import com.arafat1419.collektr.domain.model.cat.CatFact
import com.arafat1419.collektr.domain.repository.CatRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetCatFactUseCase(
    private val catRepository: CatRepository
) {
    suspend operator fun invoke(): Flow<Resource<CatFact>> {
        return catRepository.getCatFact()
    }
}