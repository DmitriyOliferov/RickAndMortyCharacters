package com.oliferov.rickandmortyapi.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocationDto (
    @SerializedName("name")
    @Expose
    val name: String
)