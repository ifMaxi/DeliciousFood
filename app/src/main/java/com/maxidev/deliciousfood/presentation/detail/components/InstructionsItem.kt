package com.maxidev.deliciousfood.presentation.detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.presentation.ui.theme.lobster
import com.maxidev.deliciousfood.presentation.ui.theme.openSans

@Composable
fun InstructionsItem(
    modifier: Modifier = Modifier,
    strInstructions: String
) {
    var expanded by remember { mutableStateOf(false) }
    val collapseText = strInstructions.split("\n").take(7).joinToString("\n")
    val contentText = if (expanded) strInstructions else collapseText

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(
            text = "Instructions",
            fontSize = 30.sp,
            fontFamily = lobster,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        OutlinedCard(
            elevation = CardDefaults.outlinedCardElevation(8.dp),
            shape = RoundedCornerShape(5),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow,
                            visibilityThreshold = null
                        )
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = contentText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = if (expanded) Int.MAX_VALUE else 7,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis
                )
            }
            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = { expanded = !expanded },
                interactionSource = remember { MutableInteractionSource() },
            ) {
                Text(
                    text = if (expanded) "Read Less" else "Read More",
                    fontFamily = openSans
                )
            }
        }
    }
}