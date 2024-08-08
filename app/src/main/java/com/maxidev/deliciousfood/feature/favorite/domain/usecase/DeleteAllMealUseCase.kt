package com.maxidev.deliciousfood.feature.favorite.domain.usecase

import com.maxidev.deliciousfood.feature.favorite.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteAllMealUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke() = repository.deleteAll()
}