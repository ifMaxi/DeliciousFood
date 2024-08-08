package com.maxidev.deliciousfood.feature.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.maxidev.deliciousfood.core_local.data.local.AppDataBase
import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.home.data.datasource.RandomMealDataSource
import com.maxidev.deliciousfood.feature.home.data.paging.CategoriesPagingSource
import com.maxidev.deliciousfood.feature.home.data.paging.SearchMealRemoteMediator
import com.maxidev.deliciousfood.feature.home.domain.mapper.toExternalModel
import com.maxidev.deliciousfood.feature.home.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal
import com.maxidev.deliciousfood.feature.home.domain.model.SearchMeal
import com.maxidev.deliciousfood.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val randomMealDataSource: RandomMealDataSource,
    private val api: ApiService,
    private val database: AppDataBase
): HomeRepository {

    override suspend fun fetchRandomMeal(): RandomAndCategoryMeal =
        randomMealDataSource.dataSourceRandomMeal()

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchSearchData(s: String): Flow<PagingData<SearchMeal>> {
        val pagingSourceFactory = { database.searchMealDao().searchPagingSource(query = s) }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            remoteMediator = SearchMealRemoteMediator(
                database = database,
                api = api,
                sQuery = s
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toExternalModel() }
            }
            .catch { if (s.isBlank()) throw it else emit(PagingData.empty()) }
    }

    override fun fetchCategories(): Flow<PagingData<CategoriesMeal>> {
        val pagingSourceFactory = { CategoriesPagingSource(apiService = api) }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map {
                val filteredResponse = mutableSetOf<String>()

                it.filter { data ->
                    if (filteredResponse.contains(data.idCategory)) false else {
                        filteredResponse.add(data.idCategory)
                    }
                }
            }
    }
}