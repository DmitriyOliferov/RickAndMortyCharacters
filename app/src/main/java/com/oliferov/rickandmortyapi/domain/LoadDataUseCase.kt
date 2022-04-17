package com.oliferov.rickandmortyapi.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke() = repository.loadData()
}