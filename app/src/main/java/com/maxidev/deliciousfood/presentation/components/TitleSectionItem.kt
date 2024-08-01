package com.maxidev.deliciousfood.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TitleSectionItem(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Black
        )
    }
}