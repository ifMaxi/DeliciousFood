package com.maxidev.deliciousfood.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SearchState(
    val sQuery: MutableState<String> = mutableStateOf("")
)