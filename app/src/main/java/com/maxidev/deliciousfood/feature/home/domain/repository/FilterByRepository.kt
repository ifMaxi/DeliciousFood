package com.maxidev.deliciousfood.feature.home.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal
import kotlinx.coroutines.flow.Flow

interface FilterByRepository {

    fun fetchFilterByCategory(c: String): Flow<PagingData<RandomAndCategoryMeal>>
}