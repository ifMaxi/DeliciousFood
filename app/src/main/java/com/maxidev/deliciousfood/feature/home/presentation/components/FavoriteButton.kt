package com.maxidev.deliciousfood.feature.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxidev.deliciousfood.core.compose_components.ButtonIconWithTextItem

// Favorite button.
@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    navigateToFavorites: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ButtonIconWithTextItem(
            text = "Favorites",
            icon = Icons.Filled.Favorite,
            onClick = navigateToFavorites
        )
    }
}