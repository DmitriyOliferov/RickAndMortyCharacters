package com.oliferov.rickandmortyapi.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.data.network.ApiFactory
import com.oliferov.rickandmortyapi.data.worker.DataWorker
import com.oliferov.rickandmortyapi.domain.Character
import com.oliferov.rickandmortyapi.domain.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val mapper: CharacterMapper,
    private val application: Application,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getCharactersList(): LiveData<List<Character>> {
        return Transformations.map(characterDao.getCharactersList()) {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override fun getCharacter(id: Int): LiveData<Character> {
        return Transformations.map(characterDao.getCharacter(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            DataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            DataWorker.makeRequest()
        )
    }
}


