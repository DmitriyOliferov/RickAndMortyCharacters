package com.oliferov.rickandmortyapi.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageDto (
    @SerializedName("info")
    @Expose
    val nextPageDto: NextPageDto? = null,
    @SerializedName("results")
    @Expose
    val charactersList: List<JsonObject>? = null
)