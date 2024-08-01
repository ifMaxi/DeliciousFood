package com.maxidev.deliciousfood.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.maxidev.deliciousfood.data.local.AppDataBase
import com.maxidev.deliciousfood.data.local.entity.CategoriesEntity
import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.domain.mappers.asEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CategoriesRemoteMediator(
    private val dataBase: AppDataBase,
    private val api: ApiService
): RemoteMediator<Int, CategoriesEntity>() {

    private val categoriesDao = dataBase.categoriesDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CategoriesEntity>
    ): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?:
                return MediatorResult.Success(true)

                lastItem.idCategory
            }
        }

        return try {
            val response = api.getCategories(page = loadKey)

            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    categoriesDao.clearAll()
                }

                response.asEntity().let { categoriesDao.saveCategories(it) }
            }

            MediatorResult.Success(endOfPaginationReached = true)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}