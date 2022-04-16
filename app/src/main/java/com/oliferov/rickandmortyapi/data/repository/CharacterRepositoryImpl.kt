package com.oliferov.rickandmortyapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.data.network.ApiFactory
import com.oliferov.rickandmortyapi.data.network.ApiService
import com.oliferov.rickandmortyapi.domain.Character
import com.oliferov.rickandmortyapi.domain.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val mapper: CharacterMapper,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getCharactersList(): LiveData<List<Character>> {
        return Transformations.map(characterDao.getCharactersList()) {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override fun getCharacter(id: Int): LiveData<Character> {
        return Transformations.map(characterDao.getCharacter(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        LoadData()
    }

    inner class LoadData() {
        suspend fun invoke() {
            var page = "1"
            while (page != "null") {
                val pageDto = ApiFactory.apiService.getAllCharacters(page)
                val characterListDto = pageDto.charactersList ?: return
                val characterDbModelList = mapper.mapDtoListToDbModelList(characterListDto)
                characterDao.insertCharactersList(characterDbModelList)
                page = pageDto.nextPageDto?.nextPage?.replace(
                    "https://rickandmortyapi.com/api/character/?page=",
                    "",
                    true
                ) ?: "null"
            }
        }
    }
}