package com.maxidev.deliciousfood.domain.mappers

import com.maxidev.deliciousfood.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.domain.model.RandomMeal

fun MealDTO.toRandomExternalModel() =
    this.meals?.firstOrNull().let { data ->
        RandomMeal(
            id = data?.idMeal.toString(),
            strMeal = data?.strMeal.toString(),
            strMealThumb = data?.strMealThumb.toString()
        )
    }