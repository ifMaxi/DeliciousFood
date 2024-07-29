package com.maxidev.deliciousfood.presentation.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.deliciousfood.domain.model.MealDetail
import com.maxidev.deliciousfood.presentation.components.CoilItem
import com.maxidev.deliciousfood.presentation.components.DialogItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel,
    navigateToDetail: (String) -> Unit
) {
    val favoriteState by viewModel.state.collectAsStateWithLifecycle()
    var openDialog by remember { mutableStateOf(false) }

    ContentListItem(
        modifier = modifier,
        favoriteMeals = favoriteState.favoriteMeals,
        deleteAll = { openDialog = true },
        mealId = navigateToDetail
    )

    if (openDialog) {
        DialogItem(
            onConfirm = {
                viewModel.deleteAll()
                openDialog = false
            },
            onDismiss = { openDialog = false },
            onDismissRequest = { openDialog = false }
        )
    }
}

@Composable
private fun ContentListItem(
    modifier: Modifier = Modifier,
    favoriteMeals: List<MealDetail>,
    lazyState: LazyListState = rememberLazyListState(),
    deleteAll: () -> Unit,
    mealId: (String) -> Unit
) {
    val favoriteState = remember(favoriteMeals) { favoriteMeals }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            TitleWithDeleteAllItem(
                title = "Favorites",
                deleteAll = deleteAll
            )
        }
        items(
            items = favoriteState,
            key = { it.idMeal },
            contentType = { it.idMeal }
        ) { item ->
            CardInformationItem(
                image = item.strMealThumb,
                title = item.strMeal,
                mealId = { mealId(item.idMeal) }
            )
        }
    }
}

@Composable
private fun TitleWithDeleteAllItem(
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
            fontWeight = FontWeight.Black
        )
        IconButton(onClick = deleteAll) {
            Icon(
                imageVector = Icons.Filled.DeleteSweep,
                contentDescription = "Delete all."
            )
        }
    }
}

@Composable
private fun CardInformationItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    mealId: () -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { mealId() },
        shape = RoundedCornerShape(5)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoilItem(
                imageUrl = image,
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .clip(RoundedCornerShape(5))
            )
            Text(
                text = title,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .width(200.dp)
            )
        }
    }
}