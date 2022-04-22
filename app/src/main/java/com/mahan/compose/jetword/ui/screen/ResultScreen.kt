package com.mahan.compose.jetword.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel

@Composable
fun ResultScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val result by viewModel.result

    Text(text = "game mode: ${result?.numberOfCorrectAnswers}")
}