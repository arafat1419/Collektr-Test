package com.arafat1419.collektr.domain.usecase.cat

import com.arafat1419.collektr.domain.model.cat.CatFact
import com.arafat1419.collektr.domain.repository.CatRepository
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

class GetCatFactUseCaseImpl(
    private val catRepository: CatRepository
) : GetCatFactUseCase {
    override suspend operator fun invoke(): Flow<Resource<CatFact>> {
        return catRepository.getCatFact()
    }
}

