package com.maxidev.deliciousfood.feature.favorite.domain.usecase

import javax.inject.Inject

data class FavoriteUseCases @Inject constructor(
    val getMeals: GetAllMealUseCase,
    val deleteAll: DeleteAllMealUseCase
)