package com.maxidev.deliciousfood.feature.detail.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.maxidev.deliciousfood.core.compose_components.CoilItem

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    strMealThumb: String
) {
    val roundedCorner = RoundedCornerShape(5)

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = roundedCorner,
        elevation = CardDefaults.outlinedCardElevation(8.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        CoilItem(
            imageUrl = strMealThumb,
            modifier = Modifier
                .fillMaxWidth()
                .clip(roundedCorner)
        )
    }
}