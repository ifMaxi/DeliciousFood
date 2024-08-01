package com.maxidev.deliciousfood.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestinations {

    @Serializable
    data object HomeScreen : NavDestinations()

    @Serializable
    data object FavoritesScreen : NavDestinations()

    @Serializable
    data object CategoriesScreen : NavDestinations()

    @Serializable
    data class DetailLocalScreen(val id: String) : NavDestinations()

    @Serializable
    data class DetailRemoteScreen(val id: String) : NavDestinations()

    @Serializable
    data class FilterCategoryScreen(val category: String) : NavDestinations()
}