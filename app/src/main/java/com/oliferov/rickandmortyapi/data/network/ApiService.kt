package com.oliferov.rickandmortyapi.data.network

import com.oliferov.rickandmortyapi.data.network.model.PageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET()
    suspend fun getAllCharacters(
        @Query(QUERY_PARAM_PAGE) page: String
    ): PageDto

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
    }
}