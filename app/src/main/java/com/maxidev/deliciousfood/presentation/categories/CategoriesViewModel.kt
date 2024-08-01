package com.maxidev.deliciousfood.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.deliciousfood.domain.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    repository: CategoriesRepository
): ViewModel() {

    init {
        Log.i("CategoriesViewModel", "CategoriesViewModel created.")
    }

    val flowCategories = repository.fetchCategories().cachedIn(viewModelScope)


    override fun onCleared() {
        super.onCleared()
        Log.i("CategoriesViewModel", "CategoriesViewModel destroyed.")
    }
}