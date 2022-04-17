package com.oliferov.rickandmortyapi.data.repository

import android.annotation.SuppressLint
import android.app.Application
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
        return Transformations.map(characterDao.getCharactersList()) {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override fun getCharacter(id: Int): LiveData<Character> {
        return Transformations.map(characterDao.getCharacter(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    @SuppressLint("CheckResult")
    override suspend fun loadData() {

        var nextPage = "1"
        while (nextPage != "null") {
            val pageDto = ApiFactory.apiService.getAllCharacters(page = nextPage)
            nextPage = pageDto
                .nextPageDto
                ?.nextPage
                ?.let {
                    it.substring(
                        it.lastIndexOf("=") + 1
                    )
                } ?: "null"
            mapper.mapJsonCharacterListToCharactersList(pageDto)
                .let {
                    mapper.mapDtoListToDbModelList(it)
                }
                .let {
                    characterDao.insertCharactersList(it)
                }
        }
    }
}


