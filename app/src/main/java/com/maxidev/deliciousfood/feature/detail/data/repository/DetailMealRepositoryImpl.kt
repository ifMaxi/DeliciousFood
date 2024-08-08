package com.maxidev.deliciousfood.feature.detail.data.repository

import com.maxidev.deliciousfood.core_local.data.local.dao.DetailsDao
import com.maxidev.deliciousfood.core_local.data.local.entity.DetailsEntity
import com.maxidev.deliciousfood.feature.detail.data.datasource.DetailsMealDataSource
import com.maxidev.deliciousfood.feature.detail.domain.mapper.toEntity
import com.maxidev.deliciousfood.feature.detail.domain.mapper.toExternalModel
import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail
import com.maxidev.deliciousfood.feature.detail.domain.repository.DetailMealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailMealRepositoryImpl @Inject constructor(
    private val dataSource: DetailsMealDataSource,
    private val dao: DetailsDao
): DetailMealRepository {

    override suspend fun fetchDetails(id: String): MealDetail = dataSource.dataSourceDetail(id)

    override suspend fun saveItem(meal: MealDetail) = dao.upsertDetails(meal.toEntity())

    override suspend fun deleteItem(meal: MealDetail) = dao.deleteDetails(meal.toEntity())

    override fun getMealById(id: String): Flow<List<MealDetail>> = dao.getMealById(id).map {
        it.map(DetailsEntity::toExternalModel)
    }
}