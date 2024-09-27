package com.arafat1419.collektr.data.mapper

import com.arafat1419.collektr.data.network.model.cat.CatFactResponse
import com.arafat1419.collektr.domain.model.cat.CatFact

object MapperResponseExtensions {
    fun CatFactResponse.toDomain(): CatFact = CatFact(
        fact = fact,
        length = length
    )
}