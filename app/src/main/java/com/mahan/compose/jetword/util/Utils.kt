package com.mahan.compose.jetword.util

import androidx.compose.ui.graphics.Color

fun getLevelColor(mode: String): Color {
    return when(mode) {
        GameMode.Easy.name -> Color.Green
        GameMode.Medium.name -> Color.Yellow
        GameMode.Hard.name -> Color.Red
        else -> Color.Black
    }
}