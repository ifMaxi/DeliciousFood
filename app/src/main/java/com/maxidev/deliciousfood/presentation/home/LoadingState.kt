package com.maxidev.deliciousfood.presentation.home

import com.maxidev.deliciousfood.domain.model.RandomAndCategoryMeal

sealed interface LoadingState {
    data class Success(val onSuccess: RandomAndCategoryMeal) : LoadingState
    data class Error(val onError: Exception) : LoadingState
}