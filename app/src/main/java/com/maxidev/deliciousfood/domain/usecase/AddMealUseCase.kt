package com.maxidev.deliciousfood.domain.usecase

import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.domain.repository.DetailMealRepository
import javax.inject.Inject

class AddMealUseCase @Inject constructor(
    private val repository: DetailMealRepository
) {
    suspend operator fun invoke(meal: MealDetail) = repository.saveItem(meal)
}