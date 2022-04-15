package com.mahan.compose.jetword.ui.screen


import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.navigation.Destination
import com.mahan.compose.jetword.ui.component.LevelButton
import com.mahan.compose.jetword.util.GameMode
import java.util.logging.Handler


@Composable
fun HomeScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val words by viewModel.allWords
    Log.d("Words", "${words.data?.size}")
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
            GameMode.values().forEach {
                LevelButton(
                    text = it.name,
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 4.dp,
                    onClick = {
                        //Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                        viewModel.setupGame(it)
                        navController.navigate(route = Destination.GameScreen.name)
                    }
                )
            }
        }
    }
}