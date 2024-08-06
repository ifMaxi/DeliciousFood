package com.maxidev.deliciousfood.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.deliciousfood.domain.usecase.FavoriteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCases: FavoriteUseCases
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    val state: StateFlow<FavoriteState> =
        favoriteUseCases.getMeals().map { FavoriteState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = FavoriteState()
            )

    fun deleteAll() = viewModelScope.launch(ioDispatcher) {
        favoriteUseCases.deleteAll()
    }
}