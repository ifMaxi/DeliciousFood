package com.maxidev.deliciousfood.domain.repository

import androidx.paging.PagingData
import com.maxidev.deliciousfood.domain.model.CategoriesMeal
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun fetchCategories(): Flow<PagingData<CategoriesMeal>>
}