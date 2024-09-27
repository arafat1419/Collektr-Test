package com.arafat1419.collektr.data.network.api

import com.arafat1419.collektr.data.network.model.cat.CatFactResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface CatService {
    @GET("fact")
    suspend fun getCatFact(): Response<CatFactResponse>
}