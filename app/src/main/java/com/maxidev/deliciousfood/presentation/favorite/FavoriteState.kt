package com.maxidev.deliciousfood.presentation.favorite

import com.maxidev.deliciousfood.domain.model.MealDetail

data class FavoriteState(
    val favoriteMeals: List<MealDetail> = emptyList()
)