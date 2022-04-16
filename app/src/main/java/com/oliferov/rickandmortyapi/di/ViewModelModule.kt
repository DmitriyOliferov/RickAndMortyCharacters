package com.oliferov.rickandmortyapi.di

import androidx.lifecycle.ViewModel
import com.oliferov.rickandmortyapi.presentation.CharacterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    fun bindCharacterViewModel(viewModel: CharacterViewModel): ViewModel
}