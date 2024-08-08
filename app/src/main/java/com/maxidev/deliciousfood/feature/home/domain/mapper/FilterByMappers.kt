package com.maxidev.deliciousfood.feature.home.domain.mapper

import com.maxidev.deliciousfood.core_remote.data.remote.dto.FilterByCategoryDTO
import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal

fun FilterByCategoryDTO.asExternalModel() =
    this.meals?.map { data ->
        RandomAndCategoryMeal(
            strMeal = data?.strMeal ?: "",
            strMealThumb = data?.strMealThumb ?: "",
            idMeal = data?.idMeal ?: ""
        )
    }