package com.maxidev.deliciousfood.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.domain.model.RandomAndCategoryMeal
import kotlinx.coroutines.flow.Flow

interface FilterByRepository {

    fun fetchFilterByCategory(c: String): Flow<PagingData<RandomAndCategoryMeal>>
}