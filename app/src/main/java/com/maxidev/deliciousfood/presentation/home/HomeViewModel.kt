package com.maxidev.deliciousfood.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.deliciousfood.domain.model.RandomMeal
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

    private val _loadState: MutableStateFlow<HomeLoadingState> =
        MutableStateFlow(HomeLoadingState.Success(RandomMeal()))
    val loadState: StateFlow<HomeLoadingState>
        get() = _loadState.asStateFlow()

    init {
        Log.i("HomeViewModel", "HomeViewModel created.")
        loadStateHome()
    }

    private fun loadStateHome() = viewModelScope.launch {
        //_loadState.value = HomeLoadingState.Loading

        _loadState.value = try {
            HomeLoadingState.Success(onSuccess = repository.fetchRandomMeal())
        } catch (e: IOException) {
            HomeLoadingState.Error(onError = e)
        } catch (e: HttpException) {
            HomeLoadingState.Error(onError = e)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("HomeViewModel", "HomeViewModel destroyed.")
    }
}