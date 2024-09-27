package com.arafat1419.collektr.domain.repository

import com.arafat1419.collektr.domain.model.cat.CatFact
import com.arafat1419.collektr.domain.vo.Resource
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun getCatFact(): Flow<Resource<CatFact>>
}