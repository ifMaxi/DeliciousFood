package com.maxidev.deliciousfood.presentation.components

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.presentation.ui.theme.openSans

@Composable
fun LoadStateScreen(
    modifier: Modifier = Modifier,
    @RawRes animation: Int?,
    text: String?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieItem(
            animation = animation ?: 0,
        )
        Text(
            text = text ?: "",
            fontSize = 24.sp,
            fontFamily = openSans,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}