package com.maxidev.deliciousfood.presentation.detail

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.presentation.detail.components.DetailRemoteContent
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