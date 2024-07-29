package com.maxidev.deliciousfood.presentation.detail

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.presentation.detail.components.DetailLocalContent

@Composable
fun DetailLocalScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    id: String,
    popBack: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.mealDetailById.collectAsStateWithLifecycle()

    LaunchedEffect(String) {
        viewModel.getMealById(id)
    }

    if (state.isNotEmpty()) {
        LoadingData(
            modifier = modifier,
            model = state.first(),
            context = context,
            onDelete = {
                viewModel.delete(it)
                popBack()
            }
        )
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