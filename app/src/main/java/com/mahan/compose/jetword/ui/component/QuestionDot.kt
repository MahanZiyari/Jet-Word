package com.mahan.compose.jetword.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuestionDot(
    modifier: Modifier,
    isCorrect: Boolean
) {
    Canvas(modifier = modifier) {
        drawCircle(
            color = if (isCorrect) Color.Green else Color.DarkGray
        )
    }
}

@Preview
@Composable
private fun QuestionDotPreview() {
    QuestionDot(isCorrect = true, modifier = Modifier.size(100.dp))
}