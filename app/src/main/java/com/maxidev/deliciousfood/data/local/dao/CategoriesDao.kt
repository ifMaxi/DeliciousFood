package com.maxidev.deliciousfood.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.deliciousfood.data.local.entity.CategoriesEntity

@Dao
interface CategoriesDao {

    @Upsert
    suspend fun saveCategories(entity: List<CategoriesEntity>)

    @Query("SELECT * FROM categories_table")
    fun allCategories(): PagingSource<Int, CategoriesEntity>

    @Query("DELETE FROM categories_table")
    suspend fun clearAll()
}