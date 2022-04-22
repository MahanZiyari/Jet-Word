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

fun String.toGameMode(): GameMode {
    return when (this) {
        GameMode.Easy.name -> GameMode.Easy
        GameMode.Medium.name -> GameMode.Medium
        GameMode.Hard.name -> GameMode.Hard
        else -> GameMode.Arcade
    }
}

fun String.randomize(): String = this.random().toString()


fun setQuestionsDot(gameMode: GameMode): Int {
    return when (gameMode) {
        GameMode.Easy -> 5
        GameMode.Medium -> 10
        GameMode.Hard -> 15
        else -> 100
    }
}

fun checkUserAnswer(userAnswer: String, correctAnswer: String): Boolean =  userAnswer == correctAnswer