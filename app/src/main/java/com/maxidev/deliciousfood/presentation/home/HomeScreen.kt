package com.maxidev.deliciousfood.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flatware
import androidx.compose.material.icons.filled.Public
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.maxidev.deliciousfood.domain.model.SearchMeal
import com.maxidev.deliciousfood.presentation.components.SearchBarItem
import com.maxidev.deliciousfood.presentation.home.components.ButtonBoxWithIconAndText
import com.maxidev.deliciousfood.presentation.home.components.RandomMealItem
import com.maxidev.deliciousfood.presentation.home.components.SearchResulItem
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToDetail: (String) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToIngredients: () -> Unit,
    navigateToArea: () -> Unit
) {
    val randomState by viewModel.randomLoadState.collectAsStateWithLifecycle()
    val pagingState = viewModel.searchedMeals.collectAsLazyPagingItems()
    val query by viewModel.queryState.value.sQuery
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    var isActive by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
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
        },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        when {
            query.isBlank() -> {
                ContentLoaded(
                    modifier = modifier.padding(innerPadding),
                    randomState = randomState,
                    navigateToDetail = navigateToDetail,
                    navigateToFavorites = navigateToFavorites,
                    navigateToCategories = navigateToCategories,
                    navigateToIngredients = navigateToIngredients,
                    navigateToArea = navigateToArea,
                    lazyState = lazyListState
                )
            }
            else -> {
                PagingContent(
                    modifier = Modifier.padding(innerPadding),
                    lazyPagingModel = pagingState,
                    lazyGridState = lazyGridState,
                    mealId = navigateToDetail
                )
            }
        }
    }
}

// List of all objects that will be rendered on the main screen.
@Composable
private fun ContentLoaded(
    modifier: Modifier = Modifier,
    randomState: LoadingState,
    lazyState: LazyListState = rememberLazyListState(),
    navigateToDetail: (String) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToIngredients: () -> Unit,
    navigateToArea: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        item {
            when (randomState) {
                is LoadingState.Error -> Text(text = randomState.onError.message.toString())
                // TODO: Change error result.
                is LoadingState.Success -> {
                    RandomMealItem(
                        modifier = Modifier,
                        model = randomState.onSuccess,
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
        item {
            NavigateButtons(
                navigateToFavorites = navigateToFavorites,
                navigateToCategories = navigateToCategories,
                navigateToIngredients = navigateToIngredients,
                navigateToArea = navigateToArea
            )
        }
    }
}

// List containing all rendered objects from the search.
@Composable
private fun PagingContent(
    modifier: Modifier = Modifier,
    lazyPagingModel: LazyPagingItems<SearchMeal>,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    mealId: (String) -> Unit
) {
    val lazyPagingState = remember(lazyPagingModel) { lazyPagingModel }

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
                SearchResulItem(
                    strMeal = info.strMeal,
                    strMealThumb = info.strMealThumb,
                    mealId = { mealId(info.idMeal) }
                )
            }
        }
    }
}

// Navigation buttons.
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun NavigateButtons(
    modifier: Modifier = Modifier,
    navigateToFavorites: () -> Unit,
    navigateToCategories: () -> Unit,
    navigateToIngredients: () -> Unit,
    navigateToArea: () -> Unit
) {
    FlowColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachColumn = 2
    ) {
        ButtonBoxWithIconAndText(
            text = "Categories",
            icon = Icons.Filled.Category,
            onClick = navigateToCategories
        )
        ButtonBoxWithIconAndText(
            text = "Ingredients",
            icon = Icons.Filled.Flatware,
            onClick = navigateToIngredients
        )
        ButtonBoxWithIconAndText(
            text = "Area",
            icon = Icons.Filled.Public,
            onClick = navigateToArea
        )
        ButtonBoxWithIconAndText(
            text = "Favorites",
            icon = Icons.Filled.Favorite,
            onClick = navigateToFavorites
        )
    }
}