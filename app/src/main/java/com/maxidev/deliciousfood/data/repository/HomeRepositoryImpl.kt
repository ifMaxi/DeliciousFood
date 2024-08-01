package com.maxidev.deliciousfood.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.deliciousfood.data.local.AppDataBase
import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.data.repository.datasource.RandomMealDataSource
import com.maxidev.deliciousfood.data.repository.paging.SearchMealRemoteMediator
import com.maxidev.deliciousfood.domain.mappers.toExternalModel
import com.maxidev.deliciousfood.domain.model.RandomMeal
import com.maxidev.deliciousfood.domain.model.SearchMeal
import com.maxidev.deliciousfood.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val randomMealDataSource: RandomMealDataSource,
    private val api: ApiService,
    private val database: AppDataBase
): HomeRepository {

    override suspend fun fetchRandomMeal(): RandomMeal =
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
    }
}