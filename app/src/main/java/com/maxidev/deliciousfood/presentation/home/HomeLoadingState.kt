package com.maxidev.deliciousfood.presentation.home

import com.maxidev.deliciousfood.domain.model.RandomMeal

sealed interface HomeLoadingState {
    data class Success(val onSuccess: RandomMeal) : HomeLoadingState
    data class Error(val onError: Exception) : HomeLoadingState
    //data object Loading : HomeLoadingState
}