package com.maxidev.deliciousfood.presentation.home.filter_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.deliciousfood.domain.model.RandomAndCategoryMeal
import com.maxidev.deliciousfood.domain.repository.FilterByRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FbCViewModel @Inject constructor(
    private val repository: FilterByRepository
): ViewModel() {

    private val _queryFilter = MutableStateFlow<PagingData<RandomAndCategoryMeal>>(PagingData.empty())
    val queryFilter = _queryFilter

    fun onCategoryFilter(category: String) = viewModelScope.launch {
        repository.fetchFilterByCategory(category)
            .cachedIn(viewModelScope)
            .collect { _queryFilter.value = it }
    }
}