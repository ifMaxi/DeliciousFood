package com.maxidev.deliciousfood.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDTO(
    val categories: List<Category>
) {
    @Serializable
    data class Category(
        val idCategory: String,
        val strCategory: String,
        val strCategoryThumb: String,
        val strCategoryDescription: String
    )
}