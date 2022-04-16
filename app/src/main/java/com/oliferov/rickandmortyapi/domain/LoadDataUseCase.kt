package com.oliferov.rickandmortyapi.domain

class LoadDataUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke() = repository.loadData()
}