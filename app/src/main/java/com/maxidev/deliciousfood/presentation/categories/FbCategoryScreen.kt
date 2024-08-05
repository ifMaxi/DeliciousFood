package com.maxidev.deliciousfood.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.maxidev.deliciousfood.domain.model.FilterByCategory
import com.maxidev.deliciousfood.presentation.components.TitleSectionItem
import com.maxidev.deliciousfood.presentation.home.components.SearchResulItem

@Composable
fun FbCategoryScreen(
    modifier: Modifier = Modifier,
    viewmodel: FbCViewModel,
    category: String,
    idMeal: (String) -> Unit
) {
    val state = viewmodel.queryFilter.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(String) {
        viewmodel.onCategoryFilter(category)
    }
    
    FbCategoryContent(
        modifier = modifier,
        lazyPagingItem = state,
        lazyGridState = lazyGridState,
        idMeal = idMeal,
        title = category
    )
}

@Composable
private fun FbCategoryContent(
    modifier: Modifier = Modifier,
    lazyPagingItem: LazyPagingItems<FilterByCategory>,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    idMeal: (String) -> Unit,
    title: String
) {
    val rememberState = remember(lazyPagingItem) { lazyPagingItem }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(170.dp),
        state = lazyGridState,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            TitleSectionItem(title = title)
        }
        items(
            count = rememberState.itemCount,
            key = rememberState.itemKey { it.idMeal }
        ) { data ->
            rememberState[data]?.let { info ->
                SearchResulItem(
                    strMeal = info.strMeal,
                    strMealThumb = info.strMealThumb,
                    mealId = { idMeal(info.idMeal) }
                )
            }
        }
    }
}