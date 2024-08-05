package com.maxidev.deliciousfood.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.domain.model.RandomMeal
import com.maxidev.deliciousfood.presentation.components.CoilItem

// Renders the random item component. Contains a title about it that describes the section.
@Composable
fun RandomMealItem(
    modifier: Modifier = Modifier,
    model: RandomMeal,
    navigateToDetail: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
            //.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Today's recipe",
            fontSize = 30.sp,
            fontWeight = FontWeight.Black
        )
        RandomItem(
            strMeal = model.strMeal,
            strMealThumb = model.strMealThumb,
            navigateToDetail = { navigateToDetail(model.id) }
        )
    }
}

// This component is built with an image and the title corresponding to the recipe.
// The image uses the graphicsLayer and drawWithContent modifiers to give a shadow
// effect to the image so that the text can be seen.
@Composable
private fun RandomItem(
    modifier: Modifier = Modifier,
    strMeal: String,
    strMealThumb: String,
    navigateToDetail: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = navigateToDetail,
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            CoilItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black,
                            Color.Black,
                            Color.Transparent,
                            Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = colors,
                                startY = 0f,
                                endY = 1100f
                            ),
                            blendMode = BlendMode.DstIn
                        )
                    },
                imageUrl = strMealThumb
            )
            Row(
                modifier = Modifier
                    .matchParentSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = strMeal,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}