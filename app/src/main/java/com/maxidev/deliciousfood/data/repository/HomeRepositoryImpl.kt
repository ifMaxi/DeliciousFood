package com.maxidev.deliciousfood.data.repository

import com.maxidev.deliciousfood.data.repository.datasource.RandomMealDataSource
import com.maxidev.deliciousfood.domain.model.RandomMeal
import com.maxidev.deliciousfood.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: RandomMealDataSource
): HomeRepository {

    override suspend fun fetchRandomMeal(): RandomMeal =
        dataSource.dataSourceRandomMeal()
}