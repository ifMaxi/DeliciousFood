package com.maxidev.deliciousfood.presentation.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.deliciousfood.domain.model.SearchMeal
import com.maxidev.deliciousfood.domain.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMealViewModel @Inject constructor(
    private val repository: MealRepository
): ViewModel() {

    private val _queryState: MutableState<SearchState> = mutableStateOf(SearchState())
    val queryState: State<SearchState> = _queryState

    private val _searchedMeals = MutableStateFlow<PagingData<SearchMeal>>(PagingData.empty())
    val searchedMeals = _searchedMeals

    init {
        Log.i("SearchMealViewModel", "ViewModel created.")
    }

    fun onQueryChange(query: String) {
        _queryState.value.sQuery.value = query
    }

    fun onClearText(query: String = "") {
        _queryState.value.sQuery.value = query
    }

    fun flowSearch(query: String) = viewModelScope.launch {
        repository.fetchSearchData(s = query)
            .cachedIn(viewModelScope)
            .collect { _searchedMeals.value = it }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SearchMealViewModel", "ViewModel destroyed.")
    }
}