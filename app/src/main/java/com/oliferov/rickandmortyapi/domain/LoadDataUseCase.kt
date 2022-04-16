package com.oliferov.rickandmortyapi.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke() = repository.loadData()
}