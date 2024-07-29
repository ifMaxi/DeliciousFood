package com.maxidev.deliciousfood.domain.repository

import com.maxidev.deliciousfood.domain.model.MealDetail
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun deleteAll()

    fun getAllMeals(): Flow<List<MealDetail>>
}