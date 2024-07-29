package com.maxidev.deliciousfood.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDTO(
    val meals: List<Meal?>? = null
)