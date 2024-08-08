package com.maxidev.deliciousfood.feature.home.data.datasource

import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.home.domain.mapper.toExternalModel
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomMealDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val api: ApiService
) {

    suspend fun dataSourceRandomMeal(): RandomAndCategoryMeal =
        withContext(ioDispatcher) {
            api.getRandomMeal().toExternalModel()
        }
}