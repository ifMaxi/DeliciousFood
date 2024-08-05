package com.maxidev.deliciousfood.presentation.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.deliciousfood.domain.model.FilterByCategory
import com.maxidev.deliciousfood.domain.repository.FilterByRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FbCViewModel @Inject constructor(
    private val repository: FilterByRepository
): ViewModel() {

    private val _queryFilter = MutableStateFlow<PagingData<FilterByCategory>>(PagingData.empty())
    val queryFilter = _queryFilter

    init {
        Log.i("FbCViewModel", "FbCViewModel created.")
    }

    fun onCategoryFilter(category: String) = viewModelScope.launch {

        repository.fetchFilterByCategory(category)
            .cachedIn(viewModelScope)
            .collect { _queryFilter.value = it }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("FbCViewModel", "FbCViewModel destroyed.")
    }
}