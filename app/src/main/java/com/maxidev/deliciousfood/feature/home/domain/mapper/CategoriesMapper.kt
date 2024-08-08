package com.maxidev.deliciousfood.feature.home.domain.mapper

import com.maxidev.deliciousfood.core_remote.data.remote.dto.CategoriesDTO
import com.maxidev.deliciousfood.feature.home.domain.model.CategoriesMeal

fun CategoriesDTO.asExternalModel() =
    this.categories.map { data ->
        CategoriesMeal(
            idCategory = data.idCategory,
            strCategory = data.strCategory,
            strCategoryThumb = data.strCategoryThumb
        )
    }