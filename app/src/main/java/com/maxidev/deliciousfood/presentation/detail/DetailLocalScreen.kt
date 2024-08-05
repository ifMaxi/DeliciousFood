package com.maxidev.deliciousfood.presentation.detail

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.presentation.detail.components.DetailLocalContent
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