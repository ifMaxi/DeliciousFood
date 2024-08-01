package com.maxidev.deliciousfood.presentation.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.maxidev.deliciousfood.domain.model.CategoriesMeal
import com.maxidev.deliciousfood.presentation.components.CoilItem
import com.maxidev.deliciousfood.presentation.components.TitleSectionItem

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel
) {
    val categoriesState = viewModel.flowCategories.collectAsLazyPagingItems()

    CategoriesContent(
        modifier = modifier,
        lazyPagingItems = categoriesState,
        navigateToCategoryDetail = {}
    )
}

@Composable
private fun CategoriesContent(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<CategoriesMeal>,
    lazyState: LazyGridState = rememberLazyGridState(),
    navigateToCategoryDetail: (String) -> Unit
) {
    val rememberState = remember(lazyPagingItems) { lazyPagingItems }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        columns = GridCells.Adaptive(170.dp),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            TitleSectionItem(title = "Categories")
        }
        items(
            count = rememberState.itemCount,
            key = rememberState.itemKey { it.idCategory },
            contentType = rememberState.itemContentType { it.idCategory }
        ) { data ->
            rememberState[data]?.let { info ->
                CategoryInfoItem(
                    strCategory = info.strCategory,
                    strCategoryThumb = info.strCategoryThumb,
                    onClick = { navigateToCategoryDetail(info.idCategory) }
                )
            }
        }
    }
}

@Composable
private fun CategoryInfoItem(
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
                fontSize = 20.sp,
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