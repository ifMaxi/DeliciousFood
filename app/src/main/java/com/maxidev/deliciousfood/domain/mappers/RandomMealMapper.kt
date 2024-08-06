package com.maxidev.deliciousfood.domain.mappers

import com.maxidev.deliciousfood.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.domain.model.RandomAndCategoryMeal

fun MealDTO.toExternalModel() =
    this.meals?.firstOrNull().let { data ->
        RandomAndCategoryMeal(
            idMeal = data?.idMeal.toString(),
            strMeal = data?.strMeal.toString(),
            strMealThumb = data?.strMealThumb.toString()
        )
    }