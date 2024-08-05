package com.maxidev.deliciousfood.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.maxidev.deliciousfood.presentation.categories.FbCViewModel
import com.maxidev.deliciousfood.presentation.categories.FbCategoryScreen
import com.maxidev.deliciousfood.presentation.detail.DetailLocalScreen
import com.maxidev.deliciousfood.presentation.detail.DetailRemoteScreen
import com.maxidev.deliciousfood.presentation.detail.DetailsViewModel
import com.maxidev.deliciousfood.presentation.favorite.FavoriteScreen
import com.maxidev.deliciousfood.presentation.favorite.FavoriteViewModel
import com.maxidev.deliciousfood.presentation.home.HomeScreen
import com.maxidev.deliciousfood.presentation.home.HomeViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: NavDestinations = NavDestinations.HomeScreen
) {
    //val navBackStackEntry by navController.currentBackStackEntryAsState()
    //val currentDestination = navBackStackEntry?.destination

    NavHost(
        modifier = modifier
            .padding(WindowInsets.statusBars.asPaddingValues()),
        navController = navController,
        startDestination = startDestination
    ) {

        // Home.
        composable<NavDestinations.HomeScreen> {
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                viewModel = viewModel,
                navigateToDetail = {
                    navController.navigate(NavDestinations.DetailRemoteScreen(id = it))
                },
                navigateToFavorites = { navController.navigate(NavDestinations.FavoritesScreen) },
                navigateToCategoryDetail = {
                    navController.navigate(NavDestinations.FilterCategoryScreen(category = it))
                }
            )
        }

        // Favorites.
        composable<NavDestinations.FavoritesScreen> {
            val viewModel = hiltViewModel<FavoriteViewModel>()

            FavoriteScreen(
                viewModel = viewModel,
                navigateToDetail = {
                    navController.navigate(NavDestinations.DetailLocalScreen(id = it))
                }
            )
        }

        // Detail remote.
        composable<NavDestinations.DetailRemoteScreen> { backStackEntry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            val args = backStackEntry.toRoute<NavDestinations.DetailRemoteScreen>().id

            DetailRemoteScreen(
                viewModel = viewModel,
                id = args
            )
        }

        // Detail local.
        composable<NavDestinations.DetailLocalScreen> { backStackEntry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            val args = backStackEntry.toRoute<NavDestinations.DetailLocalScreen>().id

            DetailLocalScreen(
                viewModel = viewModel,
                id = args,
                popBack = { navController.popBackStack() }
            )
        }

        // Filter by category.
        composable<NavDestinations.FilterCategoryScreen> { backStackEntry ->
            val viewModel = hiltViewModel<FbCViewModel>()
            val args = backStackEntry.toRoute<NavDestinations.FilterCategoryScreen>().category

            FbCategoryScreen(
                viewmodel = viewModel,
                category = args,
                idMeal = {
                    navController.navigate(NavDestinations.DetailRemoteScreen(id = it))
                }
            )
        }
    }
}