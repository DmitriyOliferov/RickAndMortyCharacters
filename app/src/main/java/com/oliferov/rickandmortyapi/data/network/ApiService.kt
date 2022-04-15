package com.oliferov.rickandmortyapi.data.network

import com.oliferov.rickandmortyapi.data.network.model.CharacterDto
import retrofit2.http.GET

interface ApiService {

    @GET("$RICK_ID,$MORTY_ID")
    suspend fun getAllCharacter(): List<CharacterDto>

    @GET(RICK_ID)
    suspend fun getRickCharacter(): List<CharacterDto>

    @GET(MORTY_ID)
    suspend fun getMortyCharacter(): List<CharacterDto>

    companion object {
        private const val RICK_ID = "1"
        private const val MORTY_ID = "2"
    }
}