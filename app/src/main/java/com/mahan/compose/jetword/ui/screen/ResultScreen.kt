package com.mahan.compose.jetword.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.model.Result
import com.mahan.compose.jetword.util.GameMode

@Composable
fun ResultScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val result by viewModel.result
    Content(result = result!!)
}


@Composable
private fun Content(result: Result) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congratulation",
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.typography.h5.fontSize
                    )
                ) {
                    append("You Finished Game on ")
                }

                withStyle(
                    SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append(result.gameMode.name)
                }
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Your Score is:  ${result.numberOfCorrectAnswers}",
            color = MaterialTheme.colors.onBackground,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}

@Preview
@Composable
private fun ResultScreenPreview() {
    Content(result = Result(gameMode = GameMode.Easy, numberOfCorrectAnswers = 4))
}