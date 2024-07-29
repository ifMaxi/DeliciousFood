package com.maxidev.deliciousfood.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SearchState(
    val sQuery: MutableState<String> = mutableStateOf("")
)