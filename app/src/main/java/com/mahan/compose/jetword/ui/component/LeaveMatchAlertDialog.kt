package com.mahan.compose.jetword.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeaveMatchAlertDialog(
    isOpen: Boolean,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    if (!isOpen) return

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "Yes I'm Sure",
                    fontSize = 14.sp
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "No Let's Continue",
                    fontSize = 14.sp
                )
            }
        },
        title = {
            Text(
                text = "Leaving so soon?",
                style = MaterialTheme.typography.subtitle1
            )
        },
        text = {
            Text(text = "Are you sure to leave the round?\nyour current progress will be LOST")
        }
    )
}