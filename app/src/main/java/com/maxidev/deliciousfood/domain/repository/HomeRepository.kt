package com.maxidev.deliciousfood.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.domain.model.RandomMeal
import com.maxidev.deliciousfood.domain.model.SearchMeal
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun fetchRandomMeal(): RandomMeal

    fun fetchSearchData(s: String): Flow<PagingData<SearchMeal>>

    fun fetchCategories(): Flow<PagingData<CategoriesMeal>>
}