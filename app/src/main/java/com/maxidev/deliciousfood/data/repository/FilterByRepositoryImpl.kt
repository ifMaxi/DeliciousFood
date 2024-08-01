package com.maxidev.deliciousfood.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.data.repository.paging.FilterByCategoryPagingSource
import com.maxidev.deliciousfood.domain.model.FilterByCategory
import com.maxidev.deliciousfood.domain.repository.FilterByRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterByRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): FilterByRepository {

    override fun fetchFilterByCategory(c: String): Flow<PagingData<FilterByCategory>> {
        val pagingSourceFactory = {
            FilterByCategoryPagingSource(
                category = c,
                apiService = apiService
            )
        }

        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map {
                val filteredResponse = mutableSetOf<String>()

                it.filter { response ->
                    if (filteredResponse.contains(response.strMeal)) {
                        false
                    } else {
                        filteredResponse.add(response.strMeal)
                    }
                }
            }
    }
}