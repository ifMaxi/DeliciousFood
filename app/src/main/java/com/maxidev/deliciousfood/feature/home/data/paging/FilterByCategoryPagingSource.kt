package com.maxidev.deliciousfood.feature.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.home.domain.mapper.asExternalModel
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal
import retrofit2.HttpException
import java.io.IOException

class FilterByCategoryPagingSource(
    private val apiService: ApiService,
    private val category: String
): PagingSource<Int, RandomAndCategoryMeal>() {

    override fun getRefreshKey(state: PagingState<Int, RandomAndCategoryMeal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RandomAndCategoryMeal> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiService.getFilterByCategory(category)
            val responseToExternal = response.asExternalModel()

            LoadResult.Page(
                data = responseToExternal.orEmpty(),
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (responseToExternal.isNullOrEmpty()) null else nextPage.plus(1)
            )
        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}