package com.maxidev.deliciousfood.feature.home.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SearchState(
    val sQuery: MutableState<String> = mutableStateOf("")
)