package com.maxidev.deliciousfood.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.deliciousfood.data.local.entity.DetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailsDao {

    @Query("SELECT * FROM details_entity WHERE idMeal = :idMeal")
    fun getMealById(idMeal: String): Flow<List<DetailsEntity>>

    @Upsert
    suspend fun upsertDetails(details: DetailsEntity)

    @Delete
    suspend fun deleteDetails(details: DetailsEntity)
}