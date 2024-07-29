package com.maxidev.deliciousfood.presentation.detail

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.presentation.detail.components.DetailRemoteContent

@Composable
fun DetailRemoteScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    id: String
) {
    val context = LocalContext.current
    val uiState by viewModel.loadState.collectAsStateWithLifecycle()

    LaunchedEffect(String) {
        viewModel.contentDetails(id)
    }

    LoadingStates(
        modifier = modifier,
        state = uiState,
        context = context,
        onClick = {
            viewModel.saveMeal()
        }
    )
}

@Composable
private fun LoadingStates(
    modifier: Modifier = Modifier,
    state: DetailLoadingState,
    context: Context,
    onClick: () -> Unit
) {
    when (state) {
        is DetailLoadingState.Error -> Text(text = "Error")
        DetailLoadingState.DetailLoading -> Text(text = "Loading")
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