package com.maxidev.deliciousfood.domain.mappers

import com.maxidev.deliciousfood.data.local.entity.SearchMealEntity
import com.maxidev.deliciousfood.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.domain.model.SearchMeal

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
