package com.mahan.compose.jetword.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.navigation.Destination
import com.mahan.compose.jetword.ui.component.LevelButton
import com.mahan.compose.jetword.util.GameMode


@Composable
fun HomeScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {

    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Column(
                modifier = Modifier.padding(top = 100.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Jet Word",
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                )

                Text(text = "Test your vocabulary")
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..1) {
                    val difficulty = GameMode.values()[i]
                    LevelButton(
                        text = difficulty.name,
                        backgroundColor = MaterialTheme.colors.background,
                        elevation = 4.dp,
                        onClick = {
                            //Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                            viewModel.setupGame(difficulty)
                            navController.navigate(route = Destination.GameScreen.name)
                        },
                        modifier = Modifier
                            .weight(50f)
                            .padding(4.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 2..3) {
                    val difficulty = GameMode.values()[i]
                    LevelButton(
                        text = difficulty.name,
                        backgroundColor = MaterialTheme.colors.background,
                        elevation = 4.dp,
                        onClick = {
                            //Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                            viewModel.setupGame(difficulty)
                            navController.navigate(route = Destination.GameScreen.name)
                        },
                        modifier = Modifier
                            .weight(50f)
                            .padding(4.dp)
                    )
                }
            }

            // End of Difficulty selection

            LevelButton(
                text = "How To Play",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                backgroundColor = MaterialTheme.colors.background,
                elevation = 4.dp,
                onClick = {
                    TODO("Implement Help AlertDialog")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(4.dp)
            )

            LevelButton(
                text = "About Developer",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                backgroundColor = MaterialTheme.colors.background,
                elevation = 4.dp,
                onClick = {
                    //TODO("Navigation To About Screen")
                    navController.navigate(route = Destination.AboutScreen.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(4.dp)
            )

        }
    }
}