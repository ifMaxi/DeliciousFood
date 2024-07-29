package com.maxidev.deliciousfood.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.maxidev.deliciousfood.presentation.detail.DetailLocalScreen
import com.maxidev.deliciousfood.presentation.detail.DetailRemoteScreen
import com.maxidev.deliciousfood.presentation.detail.DetailsViewModel
import com.maxidev.deliciousfood.presentation.favorite.FavoriteScreen
import com.maxidev.deliciousfood.presentation.favorite.FavoriteViewModel
import com.maxidev.deliciousfood.presentation.home.HomeScreen
import com.maxidev.deliciousfood.presentation.home.HomeViewModel
import com.maxidev.deliciousfood.presentation.search.SearchMealViewModel
import com.maxidev.deliciousfood.presentation.search.SearchScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: NavDestinations = NavDestinations.HomeScreen
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = modifier,
                tonalElevation = NavigationBarDefaults.Elevation
            ) {
                NavigationBarDestinations.navigationList.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(text = stringResource(id = screen.title)) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route?.equals(screen.route) == true } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = stringResource(id = screen.title)
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable<NavDestinations.HomeScreen> {
                val viewModel = hiltViewModel<HomeViewModel>()

                HomeScreen(
                    viewModel = viewModel,
                    navigateToDetail = {
                        navController.navigate(
                            NavDestinations.DetailRemoteScreen(id = it)
                        )
                    }
                )
            }
            composable<NavDestinations.SearchScreen> {
                val viewModel = hiltViewModel<SearchMealViewModel>()

                SearchScreen(
                    viewModel = viewModel,
                    navigateToDetail = {
                        navController.navigate(
                            NavDestinations.DetailRemoteScreen(id = it)
                        )
                    }
                )
            }
            composable<NavDestinations.DetailRemoteScreen> { backStackEntry ->
                val viewmodel = hiltViewModel<DetailsViewModel>()
                val args = backStackEntry.toRoute<NavDestinations.DetailRemoteScreen>().id

                DetailRemoteScreen(
                    viewModel = viewmodel,
                    id = args
                )
            }
            composable<NavDestinations.FavoritesScreen> {
                val viewModel = hiltViewModel<FavoriteViewModel>()

                FavoriteScreen(
                    viewModel = viewModel,
                    navigateToDetail = {
                        navController.navigate(
                            NavDestinations.DetailLocalScreen(id = it)
                        )
                    }
                )
            }
            composable<NavDestinations.DetailLocalScreen> { backStackEntry ->
                val viewModel = hiltViewModel<DetailsViewModel>()
                val args = backStackEntry.toRoute<NavDestinations.DetailLocalScreen>().id

                DetailLocalScreen(
                    viewModel = viewModel,
                    id = args,
                    popBack = { navController.popBackStack() }
                )
            }
        }
    }
}