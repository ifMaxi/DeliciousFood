package com.maxidev.deliciousfood.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.domain.usecase.DetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: DetailsUseCases
): ViewModel() {

    private val ioDispatcher = Dispatchers.IO

    private val _loadState: MutableStateFlow<DetailLoadingState> =
        MutableStateFlow(DetailLoadingState.DetailLoading)
    val loadState: StateFlow<DetailLoadingState>
        get() = _loadState.asStateFlow()

    private val _mealDetailById: MutableStateFlow<List<MealDetail>> = MutableStateFlow(emptyList())
    val mealDetailById: StateFlow<List<MealDetail>>
        get() = _mealDetailById.asStateFlow()

    fun contentDetails(id: String) = viewModelScope.launch {
        _loadState.value = DetailLoadingState.DetailLoading
        delay(1500L)
        _loadState.value = try {
            DetailLoadingState.Success(onSuccess = useCases.getDetails.invoke(id))
        } catch (e: HttpException) {
            DetailLoadingState.Error(onError = e)
        } catch (e: IOException) {
            DetailLoadingState.Error(onError = e)
        }
    }

    // Saves a copy of the recipe in the database, this enables viewing the copy offline later.
    // The saved item will be visible from the favorites tab.
    fun saveMeal() = viewModelScope.launch(ioDispatcher) {
        _loadState.value.let { state ->
            if (state is DetailLoadingState.Success) {
                useCases.saveMeal(state.onSuccess)
            }
        }
    }

    // Deletes the item from the database.
    fun delete(meal: MealDetail) =
        viewModelScope.launch(ioDispatcher) {
            useCases.deleteMeal(meal)
        }

    // Get recipe from data base.
    fun getMealById(id: String) = viewModelScope.launch(ioDispatcher) {
        useCases.getMealById(id).collect { detail ->
            _mealDetailById.value = detail
        }
    }
}