package com.maxidev.deliciousfood.domain.usecase

import com.maxidev.deliciousfood.domain.repository.DetailMealRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: DetailMealRepository
) {
    suspend operator fun invoke(id: String) = repository.fetchDetails(id)
}