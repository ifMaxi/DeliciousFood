package com.maxidev.deliciousfood.presentation.home

import com.maxidev.deliciousfood.domain.model.RandomMeal

sealed interface LoadingState {
    data class Success(val onSuccess: RandomMeal) : LoadingState
    data class Error(val onError: Exception) : LoadingState
}