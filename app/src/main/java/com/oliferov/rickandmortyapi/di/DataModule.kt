package com.oliferov.rickandmortyapi.di

import android.app.Application
import com.oliferov.rickandmortyapi.data.database.AppDatabase
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.repository.CharacterRepositoryImpl
import com.oliferov.rickandmortyapi.domain.CharacterRepository
import dagger.Module
import dagger.Binds
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository

    companion object {

        @Provides
        fun provideCharacterDao(
            application: Application
        ): CharacterDao {
            return AppDatabase.getInstance(application).characterDao()
        }
    }
}