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
        var currentPage = CURRENT_PAGE
        while (currentPage != LAST_PAGE) {
            val pageDto = ApiFactory.apiService.getAllCharacters(page = currentPage)
            val listDto = pageDto.charactersList ?: emptyList()
            val listDbModel = mapper.mapDtoListToDbModelList(listDto)
            characterDao.insertCharactersList(listDbModel)
            currentPage = getNextPage(pageDto.nextPageDto?.nextPage ?: LAST_PAGE)

        }
    }

    private fun getNextPage(page: String): String {
        if (page == LAST_PAGE) return LAST_PAGE
        return page.substring(
            page.lastIndexOf(START_INDEX) + 1
        )
    }

    companion object{
        private const val CURRENT_PAGE = "1"
        private const val LAST_PAGE = "null"
        private const val START_INDEX = "="
    }
}


