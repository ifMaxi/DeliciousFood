package com.maxidev.deliciousfood.domain.usecase

import com.maxidev.deliciousfood.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteAllMealUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke() = repository.deleteAll()
}