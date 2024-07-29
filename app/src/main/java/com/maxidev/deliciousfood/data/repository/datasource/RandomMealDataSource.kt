package com.maxidev.deliciousfood.data.repository.datasource

import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.domain.mappers.toRandomExternalModel
import com.maxidev.deliciousfood.domain.model.RandomMeal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomMealDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val api: ApiService
) {

    suspend fun dataSourceRandomMeal(): RandomMeal =
        withContext(ioDispatcher) {
            api.getRandomMeal().toRandomExternalModel()
        }
}