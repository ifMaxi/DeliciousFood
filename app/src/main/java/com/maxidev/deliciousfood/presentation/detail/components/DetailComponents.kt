package com.maxidev.deliciousfood.presentation.detail.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.presentation.components.ButtonIconWithTextItem
import com.maxidev.deliciousfood.presentation.components.CoilItem
import com.maxidev.deliciousfood.presentation.ui.theme.DeliciousFoodTheme

@Composable
fun DetailRemoteContent(
    modifier: Modifier = Modifier,
    model: MealDetail,
    lazyState: LazyListState = rememberLazyListState(),
    context: Context,
    onClick: () -> Unit
) {
    val modelState = remember(model) { model }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            RecipeTitleItem(strMeal = modelState.strMeal)
        }
        item {
            ImageItem(strMealThumb = modelState.strMealThumb)
        }
        item {
            ActionButtons(
                strSource = modelState.strSource,
                strYoutube = modelState.strYoutube,
                context = context,
                deleteOrSave = "Save",
                onClick = onClick,
            )
        }
        item {
            IngredientsAndQuantitiesItem(
                ingredients = modelState.strIngredient,
                quantities = modelState.strMeasure
            )
        }
        item {
            InstructionsItem(strInstructions = modelState.strInstructions)
        }
    }
}

@Composable
fun DetailLocalContent(
    modifier: Modifier = Modifier,
    model: MealDetail,
    lazyState: LazyListState = rememberLazyListState(),
    context: Context,
    onClick: () -> Unit
) {
    val modelState = remember(model) { model }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            RecipeTitleItem(
                strMeal = modelState.strMeal
            )
        }
        item {
            ImageItem(strMealThumb = modelState.strMealThumb)
        }
        item {
            ActionButtons(
                strSource = modelState.strSource,
                strYoutube = modelState.strYoutube,
                context = context,
                deleteOrSave = "Delete",
                onClick = onClick,
            )
        }
        item {
            IngredientsAndQuantitiesItem(
                ingredients = modelState.strIngredient,
                quantities = modelState.strMeasure
            )
        }
        item {
            InstructionsItem(strInstructions = modelState.strInstructions)
        }
    }
}

@Composable
private fun RecipeTitleItem(
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
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun IngredientsAndQuantitiesItem(
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
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
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

@Composable
private fun ImageItem(
    modifier: Modifier = Modifier,
    strMealThumb: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(5),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        CoilItem(
            imageUrl = strMealThumb,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5))
        )
    }
}

@Composable
private fun InstructionsItem(
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
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Start)
        )
        Card(
            elevation = CardDefaults.cardElevation(6.dp),
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
                Text(text = if (expanded) "Read Less" else "Read More")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ActionButtons(
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

@Preview
@Composable
private fun TitleWithButtonItemPreview() {
    DeliciousFoodTheme {
        RecipeTitleItem(
            strMeal = "Meal"
        )
    }
}

@Preview
@Composable
private fun DetailPreview() {
    DeliciousFoodTheme {
        IngredientsAndQuantitiesItem(
            ingredients = listOf("Harina", "Azúcar", "Huevos", "Leche", "Mantequilla"),
            quantities = listOf("2 tazas", "1 taza", "3", "1/2 taza", "100 gramos")
        )
    }
}

@Preview
@Composable
private fun InstructionsItemPreview() {
    DeliciousFoodTheme {
        InstructionsItem(
            strInstructions = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                    "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                    "in voluptate velit esse cillum dolore eu fugiat nulla pariatur."
        )
    }
}