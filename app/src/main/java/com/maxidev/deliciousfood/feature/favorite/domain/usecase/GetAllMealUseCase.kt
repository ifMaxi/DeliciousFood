package com.maxidev.deliciousfood.feature.favorite.domain.usecase

import com.maxidev.deliciousfood.feature.favorite.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetAllMealUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke() = repository.getAllMeals()
}