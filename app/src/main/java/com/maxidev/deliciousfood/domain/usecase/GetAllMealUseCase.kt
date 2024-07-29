package com.maxidev.deliciousfood.domain.usecase

import com.maxidev.deliciousfood.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetAllMealUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke() = repository.getAllMeals()
}