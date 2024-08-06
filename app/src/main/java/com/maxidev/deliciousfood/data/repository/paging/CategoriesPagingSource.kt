package com.maxidev.deliciousfood.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.domain.mappers.asExternalModel
import com.maxidev.deliciousfood.domain.model.CategoriesMeal
import retrofit2.HttpException
import java.io.IOException

class CategoriesPagingSource(
    private val apiService: ApiService
): PagingSource<Int, CategoriesMeal>() {

    override fun getRefreshKey(state: PagingState<Int, CategoriesMeal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoriesMeal> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiService.getCategories(page = nextPage)
            val responseToExternal = response.asExternalModel()

            LoadResult.Page(
                data = responseToExternal,
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (responseToExternal.isEmpty()) null else nextPage.plus(1)
            )
        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}