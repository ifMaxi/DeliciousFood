package com.maxidev.deliciousfood.feature.home.domain.mapper

import com.maxidev.deliciousfood.core_local.data.local.entity.SearchMealEntity
import com.maxidev.deliciousfood.core_remote.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.feature.home.domain.model.SearchMeal

fun MealDTO.asEntity() =
    this.meals?.map { data ->
        SearchMealEntity(
            idMeal = data?.idMeal ?: "",
            strMeal = data?.strMeal ?: "",
            strMealThumb = data?.strMealThumb ?: ""
        )
    }

fun SearchMealEntity.toExternalModel() =
    SearchMeal(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb
    )
