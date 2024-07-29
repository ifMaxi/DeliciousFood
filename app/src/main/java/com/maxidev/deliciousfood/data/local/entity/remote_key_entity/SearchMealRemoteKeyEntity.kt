package com.maxidev.deliciousfood.data.local.entity.remote_key_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_meal_entity_remote_key")
data class SearchMealRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextKey: Int?,
    val prevKey: Int?
)