package com.maxidev.deliciousfood.core_remote.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MealDTO(
    val meals: List<Meal?>? = null
)