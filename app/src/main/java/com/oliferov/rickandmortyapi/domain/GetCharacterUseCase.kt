package com.oliferov.rickandmortyapi.domain

import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.getCharacter(id)
}