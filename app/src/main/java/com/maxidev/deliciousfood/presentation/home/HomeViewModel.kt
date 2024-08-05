package com.maxidev.deliciousfood.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.deliciousfood.domain.model.RandomMeal
import com.maxidev.deliciousfood.domain.model.SearchMeal
import com.maxidev.deliciousfood.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    private val _randomLoadState: MutableStateFlow<LoadingState> =
        MutableStateFlow(LoadingState.Success(RandomMeal()))
    val randomLoadState: StateFlow<LoadingState>
        get() = _randomLoadState.asStateFlow()

    private val _queryState: MutableState<SearchState> = mutableStateOf(SearchState())
    val queryState: State<SearchState> = _queryState

    private val _searchedMeals = MutableStateFlow<PagingData<SearchMeal>>(PagingData.empty())
    val searchedMeals = _searchedMeals

    init {
        Log.i("HomeViewModel", "HomeViewModel created.")
        loadStateHome()
    }

    // Random meal.
    private fun loadStateHome() = viewModelScope.launch {
        _randomLoadState.value = try {
            LoadingState.Success(onSuccess = repository.fetchRandomMeal())
        } catch (e: IOException) {
            LoadingState.Error(onError = e)
        } catch (e: HttpException) {
            LoadingState.Error(onError = e)
        }
    }

    // Search text field.
    fun onQueryChange(query: String) {
        _queryState.value.sQuery.value = query
    }

    fun onClearText(query: String = "") {
        _queryState.value.sQuery.value = query
    }

    // Search result paging.
    fun flowSearch(query: String) = viewModelScope.launch {
        repository.fetchSearchData(s = query)
            .cachedIn(viewModelScope)
            .collect { _searchedMeals.value = it }
    }

    // Paged categories.
    val flowCategories = repository.fetchCategories().cachedIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        Log.i("HomeViewModel", "HomeViewModel destroyed.")
    }
}