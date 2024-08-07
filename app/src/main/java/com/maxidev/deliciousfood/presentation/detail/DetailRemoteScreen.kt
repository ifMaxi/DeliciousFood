package com.maxidev.deliciousfood.presentation.detail

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
import com.maxidev.deliciousfood.R
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.presentation.components.LoadStateScreen
import com.maxidev.deliciousfood.presentation.detail.components.ActionButtons
import com.maxidev.deliciousfood.presentation.detail.components.ImageItem
import com.maxidev.deliciousfood.presentation.detail.components.IngredientsAndQuantitiesItem
import com.maxidev.deliciousfood.presentation.detail.components.InstructionsItem
import com.maxidev.deliciousfood.presentation.detail.components.RecipeTitleItem
import kotlinx.coroutines.launch

@Composable
fun DetailRemoteScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    id: String
) {
    val context = LocalContext.current
    val uiState by viewModel.loadState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(String) {
        viewModel.contentDetails(id)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        LoadingStates(
            modifier = modifier.padding(innerPadding),
            state = uiState,
            context = context,
            onClick = {
                viewModel.saveMeal()

                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Recipe saved.",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        )
    }
}

@Composable
private fun LoadingStates(
    modifier: Modifier = Modifier,
    state: DetailLoadingState,
    context: Context,
    onClick: () -> Unit
) {
    when (state) {
        is DetailLoadingState.Error -> {
            LoadStateScreen(animation = R.raw.disconnected_anim, text = "Ups!")
        }
        DetailLoadingState.DetailLoading -> {
            LoadStateScreen(animation = R.raw.dots_loading, text = null)
        }
        is DetailLoadingState.Success -> {
            DetailRemoteContent(
                modifier = modifier,
                model = state.onSuccess,
                context = context,
                onClick = onClick
            )
        }
    }
}

// Content for details remotely.
@Composable
private fun DetailRemoteContent(
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
                deleteOrSave = "Save",
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