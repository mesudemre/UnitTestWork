package com.mesutemre.unittestwork.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mesutemre.unittestwork.detail.DetailScreen
import com.mesutemre.unittestwork.main.MainScreen
import com.mesutemre.unittestwork.main.MainScreenViewModel

@Composable
fun UnitTestWorkNavGraph(
    startDestination: String,
    modifier: Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = UnitTestWorkNavigation.MainScreen.screenRoute
        ) {
            val viewModel = hiltViewModel<MainScreenViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            MainScreen(state = state)
        }

        composable(
            route = UnitTestWorkNavigation.DetailScreen.screenRoute,
            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailScreen()
        }
    }
}