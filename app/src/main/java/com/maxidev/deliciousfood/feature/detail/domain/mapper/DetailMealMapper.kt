package com.maxidev.deliciousfood.feature.detail.domain.mapper

import com.maxidev.deliciousfood.core_local.data.local.entity.DetailsEntity
import com.maxidev.deliciousfood.core_remote.data.remote.dto.MealDTO
import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail

fun MealDTO.toDetailExternalModel() =
    this.meals?.firstOrNull().let { data ->
        MealDetail(
            idMeal = data?.idMeal.toString(),
            strMeal = data?.strMeal.toString(),
            strInstructions = data?.strInstructions.toString(),
            strMealThumb = data?.strMealThumb.toString(),
            strYoutube = data?.strYoutube.toString(),
            strIngredient = listOfNotNull(
                data?.strIngredient1,
                data?.strIngredient2,
                data?.strIngredient3,
                data?.strIngredient4,
                data?.strIngredient5,
                data?.strIngredient6,
                data?.strIngredient7,
                data?.strIngredient8,
                data?.strIngredient9,
                data?.strIngredient10,
                data?.strIngredient11,
                data?.strIngredient12,
                data?.strIngredient13,
                data?.strIngredient14,
                data?.strIngredient15,
                data?.strIngredient16,
                data?.strIngredient17,
                data?.strIngredient18,
                data?.strIngredient19,
                data?.strIngredient20
            ).filter { it.isNotBlank() },
            strMeasure = listOfNotNull(
                data?.strMeasure1,
                data?.strMeasure2,
                data?.strMeasure3,
                data?.strMeasure4,
                data?.strMeasure5,
                data?.strMeasure6,
                data?.strMeasure7,
                data?.strMeasure8,
                data?.strMeasure9,
                data?.strMeasure10,
                data?.strMeasure11,
                data?.strMeasure12,
                data?.strMeasure13,
                data?.strMeasure14,
                data?.strMeasure15,
                data?.strMeasure16,
                data?.strMeasure17,
                data?.strMeasure18,
                data?.strMeasure19,
                data?.strMeasure20
            ).filter { it.isNotBlank() },
            strSource = data?.strSource.toString()
        )
    }

fun MealDetail.toEntity(): DetailsEntity =
    DetailsEntity(
        idMeal = idMeal,
        strMeal = strMeal,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strYoutube = strYoutube,
        strIngredient = strIngredient,
        strMeasure = strMeasure,
        strSource = strSource
    )

fun DetailsEntity.toExternalModel(): MealDetail =
    MealDetail(
        idMeal = idMeal,
        strMeal = strMeal,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strYoutube = strYoutube,
        strIngredient = strIngredient,
        strMeasure = strMeasure,
        strSource = strSource
    )