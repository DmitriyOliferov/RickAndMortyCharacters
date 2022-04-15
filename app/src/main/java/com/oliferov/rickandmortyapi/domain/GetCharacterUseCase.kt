package com.oliferov.rickandmortyapi.domain

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.getCharacter(id)
}