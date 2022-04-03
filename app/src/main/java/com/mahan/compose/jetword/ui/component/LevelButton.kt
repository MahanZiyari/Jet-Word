package com.mahan.compose.jetword.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahan.compose.jetword.util.GameMode
import com.mahan.compose.jetword.util.getLevelColor

@Composable
fun LevelButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    elevation: Dp,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        backgroundColor = backgroundColor,
        elevation = elevation,
        shape = RoundedCornerShape(10 .dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = getLevelColor(text)
            )
        }
    }
}

@Preview
@Composable
private fun LevelButtonPreview() {
    LevelButton(text = GameMode.Hard.name, backgroundColor = Color.White, elevation = 6.dp)
}