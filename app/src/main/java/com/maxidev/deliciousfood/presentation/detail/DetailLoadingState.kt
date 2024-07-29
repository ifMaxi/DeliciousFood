package com.maxidev.deliciousfood.presentation.detail

import com.maxidev.deliciousfood.domain.model.MealDetail

sealed interface DetailLoadingState {
    data class Success(val onSuccess: MealDetail): DetailLoadingState
    data class Error(val onError: Exception): DetailLoadingState
    data object DetailLoading: DetailLoadingState
}