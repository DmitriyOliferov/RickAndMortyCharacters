package com.oliferov.rickandmortyapi.data.worker

import android.content.Context
import androidx.work.*
import com.oliferov.rickandmortyapi.data.database.CharacterDao
import com.oliferov.rickandmortyapi.data.mapper.CharacterMapper
import com.oliferov.rickandmortyapi.data.network.ApiFactory
import com.oliferov.rickandmortyapi.data.network.ApiService
import kotlinx.coroutines.delay

class DataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val characterDao: CharacterDao,
    private val apiService: ApiService,
    private val mapper: CharacterMapper
) : CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
            try {
                var nextPage = "1"
                while (nextPage != "null") {
                    val pageDto = apiService.getAllCharacters(page = nextPage)
                    nextPage = pageDto
                        .nextPageDto
                        ?.nextPage
                        ?.let {
                            it.substring(
                                it.lastIndexOf("=") + 1
                            )
                        } ?: "null"
                    mapper.mapJsonCharacterListToCharactersList(pageDto)
                        .let {
                            mapper.mapDtoListToDbModelList(it)
                        }
                        .let {
                            characterDao.insertCharactersList(it)
                        }
                }
            } catch (e: Exception) {
            }
        return Result.success()
    }

    companion object {
        const val NAME = "DataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<DataWorker>().build()
        }
    }
}