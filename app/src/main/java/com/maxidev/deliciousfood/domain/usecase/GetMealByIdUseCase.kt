package com.maxidev.deliciousfood.domain.usecase

import com.maxidev.deliciousfood.domain.repository.DetailMealRepository
import javax.inject.Inject

class GetMealByIdUseCase @Inject constructor(
    private val repository: DetailMealRepository
) {
    operator fun invoke(id: String?) = repository.getMealById(id!!)
}