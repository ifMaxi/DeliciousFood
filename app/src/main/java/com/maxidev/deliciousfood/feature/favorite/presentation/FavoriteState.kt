package com.maxidev.deliciousfood.feature.favorite.presentation

import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail

data class FavoriteState(
    val favoriteMeals: List<MealDetail> = emptyList()
)