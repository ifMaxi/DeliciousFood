package com.maxidev.deliciousfood.feature.home.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.maxidev.deliciousfood.core_local.data.local.AppDataBase
import com.maxidev.deliciousfood.core_local.data.local.entity.SearchMealEntity
import com.maxidev.deliciousfood.core_local.data.local.entity.remote_key_entity.SearchMealRemoteKeyEntity
import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.home.domain.mapper.asEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SearchMealRemoteMediator(
    private val database: AppDataBase,
    private val api: ApiService,
    private val sQuery: String
): RemoteMediator<Int, SearchMealEntity>() {

    private val searchDao = database.searchMealDao()
    private val remoteKeyDao = database.searchMealRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchMealEntity>
    ): MediatorResult {

        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevPage
            }
            LoadType.APPEND -> {
                if (state.config.prefetchDistance == 0) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }

                val remoteKey = getRemoteKeyForLastItem(state)
                val nextPage = remoteKey?.nextKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                nextPage
            }
        }

        return try {
            val response = api.getSearchMeal(s = sQuery, page = loadKey)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    searchDao.clearAll()
                    remoteKeyDao.clearAll()
                }

                val keys = response.meals?.map { meal ->
                    SearchMealRemoteKeyEntity(
                        id = meal?.idMeal ?: "",
                        nextKey = null,
                        prevKey = null
                    )
                }
                remoteKeyDao.insertOrReplace(remoteKey = keys!!)
                response.asEntity()?.let { searchDao.upsertAll(it) }
            }

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, SearchMealEntity>
    ): SearchMealRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.idMeal?.let { id ->
                remoteKeyDao.remoteKeyByQuery(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, SearchMealEntity>
    ): SearchMealRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { meal ->
                remoteKeyDao.remoteKeyByQuery(id = meal.idMeal)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, SearchMealEntity>
    ): SearchMealRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { meal ->
                remoteKeyDao.remoteKeyByQuery(id = meal.idMeal)
            }
    }
}