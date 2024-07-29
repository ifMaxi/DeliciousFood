package com.maxidev.deliciousfood.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DialogItem(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.DeleteForever,
                contentDescription = "Warning"
            )
        },
        title = {
            Text(
                text = "Delete everything forever?",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "If you confirm, this action cannot be undone.",
                textAlign = TextAlign.Center
            )
        },
        shape = RoundedCornerShape(5),
        tonalElevation = AlertDialogDefaults.TonalElevation
    )
}