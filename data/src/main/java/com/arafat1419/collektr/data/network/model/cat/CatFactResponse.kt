package com.arafat1419.collektr.data.network.model.cat

import com.google.gson.annotations.SerializedName

data class CatFactResponse(
    @SerializedName("fact")
    val fact: String = "",
    @SerializedName("length")
    val length: Int = 0,
)
