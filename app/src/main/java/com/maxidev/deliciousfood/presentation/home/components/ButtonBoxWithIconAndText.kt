package com.maxidev.deliciousfood.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBoxWithIconAndText(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier
            .wrapContentHeight(Alignment.Top)
            .size(width = 150.dp, height = 60.dp),
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
        shape = RoundedCornerShape(20),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
        contentPadding = ButtonDefaults.TextButtonWithIconContentPadding
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}