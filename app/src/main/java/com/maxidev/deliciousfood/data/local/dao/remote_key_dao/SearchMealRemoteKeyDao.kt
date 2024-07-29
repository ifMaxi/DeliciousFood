package com.maxidev.deliciousfood.data.local.dao.remote_key_dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.deliciousfood.data.local.entity.remote_key_entity.SearchMealRemoteKeyEntity

@Dao
interface SearchMealRemoteKeyDao {

    @Upsert
    suspend fun insertOrReplace(remoteKey: List<SearchMealRemoteKeyEntity>)

    @Query("SELECT * FROM search_meal_entity_remote_key WHERE id = :id")
    suspend fun remoteKeyByQuery(id: String): SearchMealRemoteKeyEntity

    @Query("DELETE FROM search_meal_entity_remote_key")
    suspend fun clearAll()
}