package com.maxidev.deliciousfood.core_local.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details_entity")
data class DetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strYoutube: String,
    val strIngredient: List<String>,
    val strMeasure: List<String>,
    val strSource: String
)