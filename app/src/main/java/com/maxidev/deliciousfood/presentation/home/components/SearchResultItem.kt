package com.maxidev.deliciousfood.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.presentation.components.CoilItem

// When performing a search, this component will be displayed rendered on the screen.
// It contains an image and a descriptive title.
@Composable
fun SearchResulItem(
    modifier: Modifier = Modifier,
    strMeal: String,
    strMealThumb: String,
    mealId: () -> Unit
) {
    val roundedCorner = RoundedCornerShape(10)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(roundedCorner)
            .clickable { mealId() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CoilItem(
                imageUrl = strMealThumb,
                modifier = Modifier
                    .size(width = 150.dp, height = 150.dp)
                    .clip(roundedCorner)
            )
        }
        Text(
            text = strMeal,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(150.dp)
        )
    }
}