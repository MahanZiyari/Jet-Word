package com.mahan.compose.jetword.model

import com.mahan.compose.jetword.util.GameMode

data class Result(
    val gameMode: GameMode,
    val numberOfCorrectAnswers: Int
)
