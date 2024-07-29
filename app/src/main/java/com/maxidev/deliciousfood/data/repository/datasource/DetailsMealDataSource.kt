package com.maxidev.deliciousfood.data.repository.datasource

import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.domain.mappers.toDetailExternalModel
import com.maxidev.deliciousfood.domain.model.MealDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsMealDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val api: ApiService
) {

    suspend fun dataSourceDetail(id: String): MealDetail =
        withContext(ioDispatcher) {
            api.getMealById(id).toDetailExternalModel()
        }
}