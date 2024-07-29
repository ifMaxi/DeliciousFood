package com.maxidev.deliciousfood.domain.usecase

import javax.inject.Inject

data class FavoriteUseCases @Inject constructor(
    val getMeals: GetAllMealUseCase,
    val deleteAll: DeleteAllMealUseCase
)