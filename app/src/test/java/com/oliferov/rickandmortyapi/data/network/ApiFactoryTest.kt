package com.oliferov.rickandmortyapi.data.network

import android.util.Log
import com.oliferov.rickandmortyapi.core.AbstractTest
import org.junit.Assert.*
import org.junit.Test

class ApiFactoryTest{

    @Test
    suspend fun result() {
        val api = ApiFactory.apiService.getAllCharacters("1")
        Log.i("DxD",api.toString())
        assertTrue(api.toString() == "")
    }
}