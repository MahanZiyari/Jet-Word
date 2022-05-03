package com.mahan.compose.jetword.ui.screen

import androidx.activity.compose.BackHandler
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
import com.mahan.compose.jetword.model.Result
import com.mahan.compose.jetword.navigation.Destination
import com.mahan.compose.jetword.ui.component.LeaveMatchAlertDialog
import com.mahan.compose.jetword.ui.component.QuestionDot
import com.mahan.compose.jetword.util.GameMode
import com.mahan.compose.jetword.util.checkUserAnswer

@ExperimentalComposeUiApi
@Composable
fun GameScreen(
    viewModel: SharedViewModel,
    gameMode: GameMode,
    navController: NavHostController
) {

    var isAlertDialogOpen by remember {
        mutableStateOf(false)
    }
    BackHandler {
        //Todo show a AlertDialog to ensure user about leaving
        isAlertDialogOpen = true
    }

    LeaveMatchAlertDialog(
        isOpen = isAlertDialogOpen,
        onConfirm = {
            isAlertDialogOpen = false
            navController.navigate(route = Destination.HomeScreen.name) {
                popUpTo(0)
            }
        },
        onDismissRequest = {
            isAlertDialogOpen = false
        }
    )


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
                },
                onGameEnd = {
                    viewModel.setNewResult(it)
                    navController.navigate(route = Destination.ResultScreen.name)
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
    onUserTextChanged: (String) -> Unit,
    onGameEnd: (Result) -> Unit
) {

    var currentIndex by remember {
        mutableStateOf(0)
    }

    var isError by remember {
        mutableStateOf(false)
    }

    var currentWordStatus by remember {
        mutableStateOf(BooleanArray(words.size))
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
            currentWordStatus.forEach {
                QuestionDot(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(vertical = 2.dp, horizontal = 4.dp),
                    isCorrect = it
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
            ),
            isError = isError
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
                    onUserTextChanged("")
                    if (currentIndex < words.lastIndex) {
                        currentIndex++
                    } else {
                        val numberOfCorrectAnswers = currentWordStatus.count { it }
                        val result = Result(
                            gameMode = gameMode,
                            numberOfCorrectAnswers = numberOfCorrectAnswers
                        )
                        onGameEnd(result)
                    }
                },
                modifier = Modifier
                    .height(48.dp)
                    .weight(50f)
                    .padding(horizontal = 2.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Can't Guess")
            }

            Button(
                onClick = {
                    if (checkUserAnswer(userText, words[currentIndex])) {
                        onUserTextChanged("")
                        currentWordStatus[currentIndex] = true
                        if (currentIndex < words.lastIndex) {
                            currentIndex++
                        } else {
                            val numberOfCorrectAnswers = currentWordStatus.count { it }
                            val result = Result(
                                gameMode = gameMode,
                                numberOfCorrectAnswers = numberOfCorrectAnswers
                            )
                            onGameEnd(result)
                        }
                    } else isError = true
                },
                modifier = Modifier
                    .height(48.dp)
                    .weight(50f)
                    .padding(horizontal = 2.dp),
                shape = RoundedCornerShape(15.dp),
                enabled = userText.isNotEmpty()
            ) {
                Text(
                    text = if (currentIndex != words.lastIndex) "Next" else "Finish",
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
        onUserTextChanged = {},
        onGameEnd = {}
    )
}