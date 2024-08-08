package com.maxidev.deliciousfood.feature.home.domain.mapper

import com.maxidev.deliciousfood.core_remote.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal

fun MealDTO.toExternalModel() =
    this.meals?.firstOrNull().let { data ->
        RandomAndCategoryMeal(
            idMeal = data?.idMeal.toString(),
            strMeal = data?.strMeal.toString(),
            strMealThumb = data?.strMealThumb.toString()
        )
    }