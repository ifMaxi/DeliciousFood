package com.maxidev.deliciousfood.feature.detail.presentation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.feature.detail.domain.model.MealDetail
import com.maxidev.deliciousfood.feature.detail.presentation.components.ActionButtons
import com.maxidev.deliciousfood.feature.detail.presentation.components.ImageItem
import com.maxidev.deliciousfood.feature.detail.presentation.components.IngredientsAndQuantitiesItem
import com.maxidev.deliciousfood.feature.detail.presentation.components.InstructionsItem
import com.maxidev.deliciousfood.feature.detail.presentation.components.RecipeTitleItem
import kotlinx.coroutines.launch

@Composable
fun DetailLocalScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    id: String,
    popBack: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.mealDetailById.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(String) {
        viewModel.getMealById(id)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        if (state.isNotEmpty()) {
            LoadingData(
                modifier = modifier.padding(innerPadding),
                model = state.first(),
                context = context,
                onDelete = {
                    viewModel.delete(it)
                    popBack()

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Recipe deleted.",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun LoadingData(
    modifier: Modifier = Modifier,
    model: MealDetail,
    context: Context,
    onDelete: (MealDetail) -> Unit
) {
    DetailLocalContent(
        modifier = modifier,
        model = model,
        context = context,
        onClick = { onDelete(model) }
    )
}

// Content for details locally.
@Composable
private fun DetailLocalContent(
    modifier: Modifier = Modifier,
    model: MealDetail,
    lazyState: LazyListState = rememberLazyListState(),
    context: Context,
    onClick: () -> Unit
) {
    val modelState = remember(model) { model }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            RecipeTitleItem(strMeal = modelState.strMeal)
        }
        item {
            ImageItem(strMealThumb = modelState.strMealThumb)
        }
        item {
            ActionButtons(
                strSource = modelState.strSource,
                strYoutube = modelState.strYoutube,
                context = context,
                deleteOrSave = "Delete",
                onClick = onClick,
            )
        }
        item {
            IngredientsAndQuantitiesItem(
                ingredients = modelState.strIngredient,
                quantities = modelState.strMeasure
            )
        }
        item {
            InstructionsItem(strInstructions = modelState.strInstructions)
        }
    }
}