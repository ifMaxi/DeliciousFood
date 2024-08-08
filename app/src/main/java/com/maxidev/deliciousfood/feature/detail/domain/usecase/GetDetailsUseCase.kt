package com.maxidev.deliciousfood.feature.detail.domain.usecase

import com.maxidev.deliciousfood.feature.detail.domain.repository.DetailMealRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: DetailMealRepository
) {
    suspend operator fun invoke(id: String) = repository.fetchDetails(id)
}