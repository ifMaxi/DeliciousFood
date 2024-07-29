package com.maxidev.deliciousfood.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.maxidev.deliciousfood.domain.model.SearchMeal
import com.maxidev.deliciousfood.presentation.components.CoilItem
import com.maxidev.deliciousfood.presentation.components.SearchBarItem
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchMealViewModel,
    navigateToDetail: (String) -> Unit
) {
    val query by viewModel.queryState.value.sQuery
    val pagingState = viewModel.searchedMeals.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    var isActive by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.statusBars,
        topBar = {
            SearchBarItem(
                value = query,
                onValueChange = viewModel::onQueryChange,
                onSearch = {
                    scope.launch {
                        if (query.isEmpty()) isActive = false else {
                            isActive = false
                            viewModel.flowSearch(it)
                        }
                    }
                    focusManager.clearFocus()
                },
                active = isActive,
                onActiveChange = { isActive = false },
                onClearText = {
                    viewModel.onClearText()
                    focusManager.clearFocus()
                }
            )
        }
    ) { innerPadding ->
        PagingContent(
            modifier = Modifier
                .padding(innerPadding),
            lazyPagingModel = pagingState,
            mealId = navigateToDetail
        )
    }
}

@Composable
private fun PagingContent(
    modifier: Modifier = Modifier,
    lazyPagingModel: LazyPagingItems<SearchMeal>,
    mealId: (String) -> Unit
) {
    val lazyPagingState = remember(lazyPagingModel) { lazyPagingModel }
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(150.dp),
        state = lazyGridState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            count = lazyPagingState.itemCount,
            key = lazyPagingState.itemKey { it.idMeal }
        ) { data ->
            lazyPagingState[data]?.let { info ->
                SearchItem(
                    strMeal = info.strMeal,
                    strMealThumb = info.strMealThumb,
                    mealId = { mealId(info.idMeal) }
                )
            }
        }
    }
}

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    strMeal: String,
    strMealThumb: String,
    mealId: () -> Unit
) {
    val roundedCorner = RoundedCornerShape(10)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(roundedCorner)
            .clickable { mealId() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CoilItem(
                imageUrl = strMealThumb,
                modifier = Modifier
                    .size(width = 150.dp, height = 150.dp)
                    .clip(roundedCorner)
            )
        }
        Text(
            text = strMeal,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(150.dp)
        )
    }
}