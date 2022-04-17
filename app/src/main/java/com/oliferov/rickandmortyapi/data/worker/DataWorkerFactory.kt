package com.oliferov.rickandmortyapi.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.data.network.ApiService

class DataWorkerFactory(
    private val characterDao: CharacterDao,
    private val apiService: ApiService,
    private val mapper: CharacterMapper
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return DataWorker(
            appContext,
            workerParameters,
            characterDao,
            apiService,
            mapper
        )
    }
}