package com.oliferov.rickandmortyapi.presentation

import android.app.Application
import androidx.work.Configuration
import com.oliferov.rickandmortyapi.data.database.AppDatabase
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.data.network.ApiFactory
import com.oliferov.rickandmortyapi.data.worker.DataWorkerFactory
import com.oliferov.rickandmortyapi.di.DaggerApplicationComponent

class CharacterApp: Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                DataWorkerFactory(
                    AppDatabase.getInstance(this).characterDao(),
                    ApiFactory.apiService,
                    CharacterMapper()
                )
            )
            .build()
    }
}