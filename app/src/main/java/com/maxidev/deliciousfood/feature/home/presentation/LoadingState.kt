package com.maxidev.deliciousfood.feature.home.presentation

import com.maxidev.deliciousfood.feature.home.domain.model.RandomAndCategoryMeal

sealed interface LoadingState {
    data class Success(val onSuccess: RandomAndCategoryMeal) : LoadingState
    data class Error(val onError: Exception) : LoadingState
}