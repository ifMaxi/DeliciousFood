package com.maxidev.deliciousfood.presentation.favorite.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.presentation.ui.theme.lobster

@Composable
fun TitleWithDeleteAllItem(
    modifier: Modifier = Modifier,
    title: String,
    deleteAll: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
            fontFamily = lobster,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = deleteAll) {
            Icon(
                imageVector = Icons.Filled.DeleteSweep,
                contentDescription = "Delete all."
            )
        }
    }
}