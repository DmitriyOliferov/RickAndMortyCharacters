package com.oliferov.rickandmortyapi.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NextPageDto(
    @SerializedName("next")
    @Expose
    val nextPage: String
)