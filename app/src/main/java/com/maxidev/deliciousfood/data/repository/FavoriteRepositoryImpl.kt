package com.maxidev.deliciousfood.data.repository

import com.maxidev.deliciousfood.data.local.dao.FavoriteDao
import com.maxidev.deliciousfood.data.local.entity.DetailsEntity
import com.maxidev.deliciousfood.domain.mappers.toExternalModel
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
): FavoriteRepository {

    override suspend fun deleteAll() = dao.deleteAllMeals()

    override fun getAllMeals(): Flow<List<MealDetail>> = dao.getAllByName().map {
        it.map(DetailsEntity::toExternalModel)
    }
}