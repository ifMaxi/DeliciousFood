package com.maxidev.deliciousfood.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.deliciousfood.R

sealed class NavigationBarDestinations(
    val route: NavDestinations,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    data object HomeScreen : NavigationBarDestinations(
        route = NavDestinations.HomeScreen,
        title = R.string.home_screen,
        icon = Icons.Filled.Home
    )
    data object SearchScreen : NavigationBarDestinations(
        route = NavDestinations.SearchScreen,
        title = R.string.search_screen,
        icon = Icons.Filled.Search
    )
    data object FavoritesScreen : NavigationBarDestinations(
        route = NavDestinations.FavoritesScreen,
        title = R.string.favorites_screen,
        icon = Icons.Filled.Favorite
    )

    companion object {
        val navigationList = listOf(
            HomeScreen,
            SearchScreen,
            FavoritesScreen
        )
    }
}