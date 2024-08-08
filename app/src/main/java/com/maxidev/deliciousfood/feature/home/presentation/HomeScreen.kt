package com.maxidev.deliciousfood.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.maxidev.deliciousfood.R
import com.maxidev.deliciousfood.core.compose_components.LoadStateScreen
import com.maxidev.deliciousfood.core.compose_components.SearchBarItem
import com.maxidev.deliciousfood.core.compose_components.SearchResulItem
import com.maxidev.deliciousfood.core.compose_components.TitleSectionItem
import com.maxidev.deliciousfood.feature.home.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.feature.home.domain.model.SearchMeal
import com.maxidev.deliciousfood.feature.home.presentation.components.CategorySectionItem
import com.maxidev.deliciousfood.feature.home.presentation.components.FavoriteButton
import com.maxidev.deliciousfood.feature.home.presentation.components.RandomMealItem
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToDetail: (String) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToCategoryDetail: (String) -> Unit
) {
    val randomState by viewModel.randomLoadState.collectAsStateWithLifecycle()
    val pagingState = viewModel.searchedMeals.collectAsLazyPagingItems()
    val categoriesState = viewModel.flowCategories.collectAsLazyPagingItems()
    val query by viewModel.queryState.value.sQuery
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
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
                    lazyState = lazyGridState,
                    lazyPagingItems = categoriesState,
                    navigateToCategoryDetail = navigateToCategoryDetail
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
    lazyPagingItems: LazyPagingItems<CategoriesMeal>,
    lazyState: LazyGridState = rememberLazyGridState(),
    navigateToDetail: (String) -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToCategoryDetail: (String) -> Unit
) {
    val rememberState = remember(lazyPagingItems) { lazyPagingItems }

    when {
        rememberState.loadState.refresh is LoadState.Loading -> {
            LoadStateScreen(animation = R.raw.cooking_loading, text = "Cooking...")
        }
        else -> {
            LazyVerticalGrid(
                modifier = modifier
                    .fillMaxSize(),
                state = lazyState,
                columns = GridCells.Adaptive(170.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(24.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    when (randomState) {
                        is LoadingState.Error -> {
                            LoadStateScreen(animation = null, text = "Something went wrong.")
                        }
                        is LoadingState.Success -> {
                            RandomMealItem(
                                modifier = Modifier,
                                model = randomState.onSuccess,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    }
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                    FavoriteButton(navigateToFavorites = navigateToFavorites)
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                    TitleSectionItem(title = "Categories")
                }
                items(
                    count = rememberState.itemCount,
                    key = rememberState.itemKey { it.idCategory },
                    contentType = rememberState.itemContentType { it.idCategory }
                ) { data ->
                    rememberState[data]?.let { info ->
                        CategorySectionItem(
                            strCategory = info.strCategory,
                            strCategoryThumb = info.strCategoryThumb,
                            onClick = { navigateToCategoryDetail(info.strCategory) }
                        )
                    }
                }
            }
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

    when (lazyPagingState.loadState.refresh) {
        is LoadState.Loading -> {
            LoadStateScreen(animation = R.raw.search_n, text = null)
        }

        is LoadState.Error -> {
            LoadStateScreen(animation = R.raw.pepper_on_fire, text = "Ups!")
        }

        else -> {
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
                    key = lazyPagingState.itemKey { it.idMeal },
                    contentType = lazyPagingState.itemContentType { it.strMeal }
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
    }
}