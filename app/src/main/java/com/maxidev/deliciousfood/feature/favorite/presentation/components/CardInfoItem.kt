package com.maxidev.deliciousfood.feature.favorite.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.core.compose_components.CoilItem

@Composable
fun CardInfoItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    mealId: () -> Unit
) {
    val roundedCornerShape = RoundedCornerShape(5)

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { mealId() },
        shape = roundedCornerShape,
        elevation = CardDefaults.outlinedCardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoilItem(
                imageUrl = image,
                modifier = Modifier
                    .size(width = 120.dp, height = 120.dp)
                    .clip(roundedCornerShape)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        shape = roundedCornerShape
                    )
            )
            Text(
                text = title,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .width(200.dp)
            )
        }
    }
}