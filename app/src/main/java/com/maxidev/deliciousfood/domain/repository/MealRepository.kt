package com.maxidev.deliciousfood.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.domain.model.SearchMeal
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun fetchSearchData(s: String): Flow<PagingData<SearchMeal>>
}