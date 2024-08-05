package com.maxidev.deliciousfood.domain.mappers

import com.maxidev.deliciousfood.data.local.entity.CategoriesEntity
import com.maxidev.deliciousfood.data.remote.dto.CategoriesDTO
import com.maxidev.deliciousfood.domain.model.CategoriesMeal

fun CategoriesDTO.asEntity() =
    this.categories.map { data ->
        CategoriesEntity(
            idCategory = data.idCategory,
            strCategory = data.strCategory,
            strCategoryThumb = data.strCategoryThumb
        )
    }

fun CategoriesEntity.asExternalModel() =
    CategoriesMeal(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryThumb = strCategoryThumb
    )