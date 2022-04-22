package com.mahan.compose.jetword.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.ui.screen.GameScreen
import com.mahan.compose.jetword.ui.screen.HomeScreen
import com.mahan.compose.jetword.ui.screen.ResultScreen
import com.mahan.compose.jetword.ui.screen.SplashScreen

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavigation(
    viewModel: SharedViewModel
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Destination.SplashScreen.name
    ) {

        // Home Screen
        composable(route = Destination.HomeScreen.name) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }


        // Game Screen
        composable(
            route = Destination.GameScreen.name
            //route = "${Destination.GameScreen.name}/{mode}",
            //arguments = listOf(navArgument(name = "mode") { type = NavType.StringType })
        ) {
            // val gameMode = it.arguments?.getString("mode")
            val gameMode by viewModel.selectedMode
            GameScreen(
                viewModel = viewModel,
                gameMode = gameMode!!,
                navController = navController
            )
        }


        // Splash Screen
        composable(route = Destination.SplashScreen.name) {
            SplashScreen(
                viewModel = viewModel,
                navController = navController
            )
        }


        // Result Screen
        composable(route = Destination.ResultScreen.name) {
            ResultScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

    }
}