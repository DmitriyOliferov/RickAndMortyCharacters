package com.oliferov.rickandmortyapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliferov.rickandmortyapi.domain.GetCharacterUseCase
import com.oliferov.rickandmortyapi.domain.GetCharactersListUseCase
import com.oliferov.rickandmortyapi.domain.LoadDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val charactersList = getCharactersListUseCase()

    fun getCharacterDetail(id: Int) = getCharacterUseCase(id)

    init {
            loadDataUseCase()
    }
}