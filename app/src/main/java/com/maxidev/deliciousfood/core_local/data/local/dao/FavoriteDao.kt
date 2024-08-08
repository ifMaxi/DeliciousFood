package com.maxidev.deliciousfood.core_local.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.maxidev.deliciousfood.core_local.data.local.entity.DetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("DELETE FROM details_entity")
    suspend fun deleteAllMeals()

    @Query("SELECT * FROM details_entity ORDER BY strMeal ASC")
    fun getAllByName(): Flow<List<DetailsEntity>>
}