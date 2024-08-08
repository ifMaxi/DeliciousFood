package com.maxidev.deliciousfood.feature.detail.domain.model

data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strYoutube: String,
    val strIngredient: List<String>,
    val strMeasure: List<String>,
    val strSource: String
)