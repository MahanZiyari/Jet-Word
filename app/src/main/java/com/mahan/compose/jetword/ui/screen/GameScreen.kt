package com.mahan.compose.jetword.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.ui.component.QuestionDot
import com.mahan.compose.jetword.util.GameMode

@ExperimentalComposeUiApi
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
            Content(
                gameMode = gameMode,
                words = words.data!!,
                userText = viewModel.userText.value,
                onUserTextChanged = {
                    viewModel.onUserTextChange(it)
                }
            )
        else
            Text(text = "Loading")
    }
}


@ExperimentalComposeUiApi
@Composable
private fun Content(
    gameMode: GameMode,
    words: List<String>,
    userText: String,
    onUserTextChanged: (String) -> Unit
) {

    var currentIndex by remember {
        mutableStateOf(0)
    }


    val localKeyboard = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(10f)
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

        Spacer(modifier = Modifier.weight(20f))

        Text(
            text = words[currentIndex],
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(30f),
            style = MaterialTheme.typography.h3
        )

        OutlinedTextField(
            value = userText,
            onValueChange = onUserTextChanged,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.9f)
                .padding(top = 50.dp)
                .weight(30f),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    localKeyboard?.hide()
                }
            ),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h6.fontSize
            )
        )


        Spacer(modifier = Modifier.weight(70f))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(15f)
                .padding(bottom = 6.dp)
        ) {

            OutlinedButton(
                onClick = {
                    if (currentIndex < words.lastIndex)
                        currentIndex++
                },
                modifier = Modifier
                    .height(48.dp)
                    .weight(50f)
                    .padding(horizontal = 2.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = if (currentIndex != words.lastIndex) "Can't Guess" else "Finish")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(48.dp)
                    .weight(50f)
                    .padding(horizontal = 2.dp),
                shape = RoundedCornerShape(15.dp),
                enabled = userText.isNotEmpty()
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.button
                )
            }
        }

    }// Screen Column Scope
}


@ExperimentalComposeUiApi
@Preview
@Composable
private fun ContentPreview() {
    Content(
        gameMode = GameMode.Easy,
        words = arrayListOf("Mahan"),
        userText = "",
        onUserTextChanged = {}
    )
}