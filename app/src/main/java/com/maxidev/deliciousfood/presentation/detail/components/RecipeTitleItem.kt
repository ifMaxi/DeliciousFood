package com.maxidev.deliciousfood.presentation.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.presentation.ui.theme.lobster

@Composable
fun RecipeTitleItem(
    modifier: Modifier = Modifier,
    strMeal: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = strMeal,
            fontSize = 30.sp,
            fontFamily = lobster,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}