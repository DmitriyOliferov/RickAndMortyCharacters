package com.oliferov.rickandmortyapi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.data.network.ApiFactory
import com.oliferov.rickandmortyapi.domain.Character
import com.oliferov.rickandmortyapi.domain.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val mapper: CharacterMapper,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getCharactersList(): LiveData<List<Character>> {
        val list = Transformations.map(characterDao.getCharactersList()) {
            it.map {
                Log.i("Dxd", it.toString())
                mapper.mapDbModelToEntity(it)
            }
        }
        return list
    }

    override fun getCharacter(id: Int): LiveData<Character> {
        return Transformations.map(characterDao.getCharacter(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        loadInDb()
    }

    suspend fun loadInDb() {
        var currentPage = "1"
        while (currentPage != "null") {
            val pageDto = ApiFactory.apiService.getAllCharacters(page = currentPage)
            val listDto = pageDto.charactersList ?: emptyList()
            val listDbModel = mapper.mapDtoListToDbModelList(listDto)
            characterDao.insertCharactersList(listDbModel)
            currentPage = getNextPage(pageDto.nextPageDto?.nextPage ?: "null")

        }
    }

    private fun getNextPage(page: String): String {
        if (page == "null") return "null"
        return page.substring(
            page.lastIndexOf("=") + 1
        )
    }
}


