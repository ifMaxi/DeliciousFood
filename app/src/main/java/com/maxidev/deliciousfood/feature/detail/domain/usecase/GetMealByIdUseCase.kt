package com.maxidev.deliciousfood.feature.detail.domain.usecase

import com.maxidev.deliciousfood.feature.detail.domain.repository.DetailMealRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(
    private val repository: DetailMealRepository
) {
    operator fun invoke(id: String?) = repository.getMealById(id!!)
}