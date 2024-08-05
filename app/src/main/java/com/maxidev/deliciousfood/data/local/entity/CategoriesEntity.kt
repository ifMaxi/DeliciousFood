package com.maxidev.deliciousfood.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoriesEntity(
    @PrimaryKey(autoGenerate = false)
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String
)