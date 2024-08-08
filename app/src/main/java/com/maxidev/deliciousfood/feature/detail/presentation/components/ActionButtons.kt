package com.maxidev.deliciousfood.feature.detail.presentation.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.maxidev.deliciousfood.core.compose_components.ButtonIconWithTextItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    deleteOrSave: String,
    strSource: String,
    strYoutube: String,
    context: Context,
    onClick: () -> Unit
) {
    val sourceIntent = Intent(Intent.ACTION_VIEW, Uri.parse(strSource))
    val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse(strYoutube))
    val toast = Toast.makeText(context, "Link not found. ☹️", LENGTH_SHORT)

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = 2
    ) {
        ButtonIconWithTextItem(
            text = "Source",
            icon = Icons.Filled.Link,
            onClick = {
                if (strSource.isNotBlank() || strSource.isNotEmpty()) {
                    startActivity(context, sourceIntent, null)
                } else toast.show()
            }
        )
        ButtonIconWithTextItem(
            text = "YouTube",
            icon = Icons.Filled.OndemandVideo,
            onClick = {
                if (strYoutube.isNotBlank() || strYoutube.isNotEmpty()) {
                    startActivity(context, youtubeIntent, null)
                } else toast.show()
            }
        )
        ButtonIconWithTextItem(
            text = deleteOrSave,
            icon = if (deleteOrSave == "Save") Icons.Filled.Save else Icons.Filled.Delete,
            onClick = onClick
        )
    }
}