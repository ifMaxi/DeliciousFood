package com.maxidev.deliciousfood.feature.favorite.domain.repository

import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun deleteAll()

    fun getAllMeals(): Flow<List<MealDetail>>
}