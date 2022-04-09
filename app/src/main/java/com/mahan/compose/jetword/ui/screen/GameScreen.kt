package com.mahan.compose.jetword.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.ui.component.QuestionDot
import com.mahan.compose.jetword.util.GameMode
import com.mahan.compose.jetword.util.randomize

@Composable
fun GameScreen(
    viewModel: SharedViewModel,
    gameMode: GameMode,
    navController: NavHostController
) {
    val words by viewModel.activeWords
    if (gameMode == GameMode.Arcade) {

    } else {
        if (words.loading == false)
            Content(gameMode = gameMode, words = words.data!!)
        else
            Text(text = "Loading")
    }
}


@Composable
private fun Content(
    gameMode: GameMode,
    words: ArrayList<String>
) {

    var currentIndex by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            words.forEach {
                QuestionDot(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(vertical = 2.dp, horizontal = 4.dp),
                    isCorrect = false
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = words[currentIndex],
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.h3
        )
    }// Screen Column Scope
}