package com.maxidev.deliciousfood.core_local.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.deliciousfood.core_local.data.local.entity.SearchMealEntity

@Dao
interface SearchMealDao {

    @Query("SELECT * FROM search_meal_entity WHERE strMeal LIKE '%' || :query || '%'")
    fun searchPagingSource(query: String): PagingSource<Int, SearchMealEntity>

    @Upsert
    suspend fun upsertAll(meals: List<SearchMealEntity>)

    @Query("DELETE FROM search_meal_entity")
    suspend fun clearAll()
}