package com.maxidev.deliciousfood.feature.home.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.feature.home.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal
import com.maxidev.deliciousfood.feature.home.domain.model.SearchMeal
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun fetchRandomMeal(): RandomAndCategoryMeal

    fun fetchSearchData(s: String): Flow<PagingData<SearchMeal>>

    fun fetchCategories(): Flow<PagingData<CategoriesMeal>>
}