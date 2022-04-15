package com.oliferov.rickandmortyapi.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.domain.Character
import com.oliferov.rickandmortyapi.domain.CharacterRepository

class CharacterRepositoryImpl(
    private val mapper: CharacterMapper,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getCharactersList(): LiveData<List<Character>> {
        return Transformations.map(characterDao.getCharactersList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCharacter(id: Int): LiveData<Character> {
        return Transformations.map(characterDao.getCharacter(id)){
            mapper.mapDbModelToEntity(it)
        }
    }
}