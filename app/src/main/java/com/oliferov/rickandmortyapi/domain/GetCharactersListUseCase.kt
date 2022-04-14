package com.oliferov.rickandmortyapi.domain

import androidx.lifecycle.LiveData

class GetCharactersListUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke() = repository.getCharactersList()
}