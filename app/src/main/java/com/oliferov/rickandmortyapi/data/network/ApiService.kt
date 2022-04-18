package com.oliferov.rickandmortyapi.data.network

import com.oliferov.rickandmortyapi.data.network.model.ResponseDto

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINT)
    suspend fun getAllCharacters(
        @Query(QUERY_PARAM_PAGE) page: String
    ): ResponseDto

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
        private const val END_POINT = "character"
    }
}