package com.maxidev.deliciousfood.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.deliciousfood.data.local.AppDataBase
import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.data.repository.paging.CategoriesRemoteMediator
import com.maxidev.deliciousfood.domain.mappers.asExternalModel
import com.maxidev.deliciousfood.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dataBase: AppDataBase
): CategoriesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchCategories(): Flow<PagingData<CategoriesMeal>> {
        val pagingSourceFactory = { dataBase.categoriesDao().allCategories() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            remoteMediator = CategoriesRemoteMediator(
                dataBase = dataBase,
                api = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.asExternalModel() }
            }
    }
}