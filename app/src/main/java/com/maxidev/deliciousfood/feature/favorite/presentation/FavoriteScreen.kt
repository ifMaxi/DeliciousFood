package com.maxidev.deliciousfood.feature.favorite.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail
import com.maxidev.deliciousfood.core.compose_components.DialogItem
import com.maxidev.deliciousfood.feature.favorite.presentation.components.CardInfoItem
import com.maxidev.deliciousfood.feature.favorite.presentation.components.TitleWithDeleteAllItem
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel,
    navigateToDetail: (String) -> Unit
) {
    val favoriteState by viewModel.state.collectAsStateWithLifecycle()
    var openDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        ContentListItem(
            modifier = modifier.padding(innerPadding),
            favoriteMeals = favoriteState.favoriteMeals,
            deleteAll = { openDialog = true },
            mealId = navigateToDetail
        )

        if (openDialog) {
            DialogItem(
                onConfirm = {
                    viewModel.deleteAll()
                    openDialog = false

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "All recipes deleted.",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                onDismiss = { openDialog = false },
                onDismissRequest = { openDialog = false }
            )
        }
    }
}

@Composable
private fun ContentListItem(
    modifier: Modifier = Modifier,
    favoriteMeals: List<MealDetail>,
    lazyState: LazyListState = rememberLazyListState(),
    deleteAll: () -> Unit,
    mealId: (String) -> Unit
) {
    val favoriteState = remember(favoriteMeals) { favoriteMeals }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TitleWithDeleteAllItem(
                title = "Favorites",
                deleteAll = deleteAll
            )
        }
        items(
            items = favoriteState,
            key = { it.idMeal },
            contentType = { it.idMeal }
        ) { item ->
            CardInfoItem(
                image = item.strMealThumb,
                title = item.strMeal,
                mealId = { mealId(item.idMeal) }
            )
        }
    }
}