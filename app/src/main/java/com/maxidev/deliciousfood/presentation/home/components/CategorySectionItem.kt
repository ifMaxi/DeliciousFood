package com.maxidev.deliciousfood.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.presentation.components.CoilItem
import com.maxidev.deliciousfood.presentation.ui.theme.openSans

@Composable
fun CategorySectionItem(
    modifier: Modifier = Modifier,
    strCategory: String,
    strCategoryThumb: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight(Alignment.Top),
        onClick = onClick,
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = strCategory,
                fontSize = 18.sp,
                fontFamily = openSans,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            CoilItem(
                modifier = Modifier
                    .clip(RoundedCornerShape(10)),
                imageUrl = strCategoryThumb
            )
        }
    }
}