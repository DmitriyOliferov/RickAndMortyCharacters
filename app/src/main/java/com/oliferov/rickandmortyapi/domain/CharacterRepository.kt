package com.oliferov.rickandmortyapi.domain

import androidx.lifecycle.LiveData

interface CharacterRepository{

fun getCharactersList(): LiveData<List<Character>>

fun getCharacter(id: Int): LiveData<Character>

suspend fun loadData()
}