package com.maxidev.deliciousfood.feature.detail.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.deliciousfood.feature.ui.theme.lobster

@Composable
fun IngredientsAndQuantitiesItem(
    modifier: Modifier = Modifier,
    ingredients: List<String>,
    quantities: List<String>
) {
    val zipIngredients = ingredients.zip(quantities)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ingredients",
            fontSize = 30.sp,
            fontFamily = lobster,
            fontWeight = FontWeight.Bold,
            modifier = modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(20.dp))
        zipIngredients.forEach { (ingredient, quantity) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "● $ingredient: ",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = quantity,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}