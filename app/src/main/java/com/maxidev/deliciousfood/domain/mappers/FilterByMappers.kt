package com.maxidev.deliciousfood.domain.mappers

import com.maxidev.deliciousfood.data.remote.dto.FilterByCategoryDTO
import com.maxidev.deliciousfood.domain.model.RandomAndCategoryMeal

fun FilterByCategoryDTO.asExternalModel() =
    this.meals?.map { data ->
        RandomAndCategoryMeal(
            strMeal = data?.strMeal ?: "",
            strMealThumb = data?.strMealThumb ?: "",
            idMeal = data?.idMeal ?: ""
        )
    }