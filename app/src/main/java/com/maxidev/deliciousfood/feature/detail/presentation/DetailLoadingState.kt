package com.maxidev.deliciousfood.feature.detail.presentation

import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail

sealed interface DetailLoadingState {
    data class Success(val onSuccess: MealDetail): DetailLoadingState
    data class Error(val onError: Exception): DetailLoadingState
    data object DetailLoading: DetailLoadingState
}