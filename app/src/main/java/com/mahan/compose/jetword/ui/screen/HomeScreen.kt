package com.mahan.compose.jetword.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mahan.compose.jetword.SharedViewModel
import com.mahan.compose.jetword.ui.component.LevelButton
import com.mahan.compose.jetword.util.GameMode

@Composable
fun HomeScreen(
    viewModel: SharedViewModel
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
            GameMode.values().forEach {
                LevelButton(
                    text = it.name,
                    backgroundColor = MaterialTheme.colors.background,
                    elevation = 4.dp,
                    onClick = {
                        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}