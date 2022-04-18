package com.oliferov.rickandmortyapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliferov.rickandmortyapi.domain.Character
import com.oliferov.rickandmortyapi.domain.GetCharacterUseCase
import com.oliferov.rickandmortyapi.domain.GetCharactersListUseCase
import com.oliferov.rickandmortyapi.domain.LoadDataUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    var charactersList= getCharactersListUseCase()


    fun getCharacterDetail(id: Int) = getCharacterUseCase(id)

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            loadDataUseCase()
            charactersList = getCharactersListUseCase()
        }

    }
}
