package com.maxidev.deliciousfood.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.domain.mappers.asExternalModel
import com.maxidev.deliciousfood.domain.model.FilterByCategory
import retrofit2.HttpException
import java.io.IOException

class FilterByCategoryPagingSource(
    private val apiService: ApiService,
    private val category: String
): PagingSource<Int, FilterByCategory>() {

    override fun getRefreshKey(state: PagingState<Int, FilterByCategory>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilterByCategory> {
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