package com.maxidev.deliciousfood.feature.detail.domain.usecase

import javax.inject.Inject

data class DetailsUseCases @Inject constructor(
    val saveMeal: AddMealUseCase,
    val deleteMeal: DeleteMealUseCase,
    val getDetails: GetDetailsUseCase,
    val getMealById: GetMealByIdUseCase,
)