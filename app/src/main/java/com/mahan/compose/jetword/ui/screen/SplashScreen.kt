package com.mahan.compose.jetword.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.navigation.Destination

@Composable
fun SplashScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val allWords by viewModel.allWords
    if (!allWords.data.isNullOrEmpty()) {
        navController.navigate(route = Destination.HomeScreen.name) {
            popUpTo(0)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Loading Data")
    }
}