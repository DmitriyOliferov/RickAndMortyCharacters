package com.oliferov.rickandmortyapi.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CharacterDto(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("species")
    @Expose
    val species: String,
    @SerializedName("gender")
    @Expose
    val gender: String,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("image")
    @Expose
    val avatar: String,
    @SerializedName("location")
    @Expose
    val location: LocationDto,
    @SerializedName("episode")
    @Expose
    val episode: Array<String>
)