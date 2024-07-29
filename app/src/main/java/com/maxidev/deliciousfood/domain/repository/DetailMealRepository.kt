package com.maxidev.deliciousfood.domain.repository

import com.maxidev.deliciousfood.domain.model.MealDetail
import kotlinx.coroutines.flow.Flow

interface DetailMealRepository {

    suspend fun fetchDetails(id: String): MealDetail

    suspend fun saveItem(meal: MealDetail)

    suspend fun deleteItem(meal: MealDetail)

    fun getMealById(id: String): Flow<List<MealDetail>>
}