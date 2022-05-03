package com.mahan.compose.jetword.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahan.compose.jetword.R

@Composable
fun HelpAlertDialog(
    isOpen: Boolean,
    onConfirm: () -> Unit
) {

    if (!isOpen) return

    AlertDialog(
        onDismissRequest = onConfirm,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Thanks",
                    fontSize = 18.sp
                )
            }
        },
        title = {
            Text(text = "How to Play")
        },
        text = {
            Text(
                text = stringResource(id = R.string.app_help),
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        },
        shape = RoundedCornerShape(10.dp)
    )
}