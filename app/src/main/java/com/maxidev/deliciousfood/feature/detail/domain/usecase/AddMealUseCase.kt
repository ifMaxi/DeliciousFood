package com.maxidev.deliciousfood.feature.detail.domain.usecase

import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail
import com.maxidev.deliciousfood.feature.detail.domain.repository.DetailMealRepository
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val repository: DetailMealRepository
) {
    suspend operator fun invoke(meal: MealDetail) = repository.saveItem(meal)
}