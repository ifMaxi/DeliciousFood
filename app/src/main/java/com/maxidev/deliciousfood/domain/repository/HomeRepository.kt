package com.maxidev.deliciousfood.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.domain.model.RandomAndCategoryMeal
import com.maxidev.deliciousfood.domain.model.SearchMeal
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun fetchRandomMeal(): RandomAndCategoryMeal

    fun fetchSearchData(s: String): Flow<PagingData<SearchMeal>>

    fun fetchCategories(): Flow<PagingData<CategoriesMeal>>
}