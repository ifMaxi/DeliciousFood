package com.maxidev.deliciousfood.domain.mappers

import com.maxidev.deliciousfood.data.remote.dto.CategoriesDTO
import com.maxidev.deliciousfood.domain.model.CategoriesMeal

fun CategoriesDTO.asExternalModel() =
    this.categories.map { data ->
        CategoriesMeal(
            idCategory = data.idCategory,
            strCategory = data.strCategory,
            strCategoryThumb = data.strCategoryThumb
        )
    }