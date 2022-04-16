package com.oliferov.rickandmortyapi.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke() = repository.getCharactersList()
}