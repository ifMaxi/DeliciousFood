package com.maxidev.deliciousfood.feature.detail.data.datasource

import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.detail.domain.mapper.toDetailExternalModel
import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail
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