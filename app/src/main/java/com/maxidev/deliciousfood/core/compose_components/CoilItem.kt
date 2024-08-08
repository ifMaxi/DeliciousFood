package com.maxidev.deliciousfood.core.compose_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.maxidev.deliciousfood.R
import kotlinx.coroutines.Dispatchers

@Composable
fun CoilItem(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    val context = LocalContext.current
    val cachePolicy = CachePolicy.ENABLED
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .memoryCachePolicy(cachePolicy)
        .diskCachePolicy(cachePolicy)
        .dispatcher(Dispatchers.IO)
        .error(R.drawable.not_found)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}