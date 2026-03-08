package com.thmanyah.thmanyah.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thmanyah.thmanyah.home.presentation.HomeScreen
import com.thmanyah.thmanyah.search.presentation.SearchScreen

object Routes {
    const val HOME = "home"
    const val SEARCH = "search"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToSearch = {
                    navController.navigate(Routes.SEARCH)
                }
            )
        }
        composable(Routes.SEARCH) {
            SearchScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
