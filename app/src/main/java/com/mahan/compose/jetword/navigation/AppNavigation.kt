package com.mahan.compose.jetword.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.ui.screen.HomeScreen

@ExperimentalAnimationApi
@Composable
fun AppNavigation(
    viewModel: SharedViewModel
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Destination.HomeScreen.name
    ) {
        composable(route = Destination.HomeScreen.name) {
            HomeScreen(viewModel = viewModel)
        }

    }
}