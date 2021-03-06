package com.oliferov.rickandmortyapi.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE id == :id LIMIT 1")
    fun getCharacter(id: Int): LiveData<CharacterDbModel>

    @Query("SELECT * FROM character")
    fun getCharactersList(): LiveData<List<CharacterDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharactersList(charactersList: List<CharacterDbModel>)
}