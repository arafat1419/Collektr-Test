package com.arafat1419.collektr.domain.usecase.cat

import com.arafat1419.collektr.domain.model.cat.CatFact
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GetCatFactUseCase {
    suspend operator fun invoke(): Flow<Resource<CatFact>>
}