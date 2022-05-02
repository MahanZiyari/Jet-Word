package com.mahan.compose.jetword.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahan.compose.jetword.R

@Composable
fun AboutScreen(navigateToMainMenu: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "About Developer") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateToMainMenu()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Main Menu"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Developer picture",
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .clip(CircleShape)
                    .size(150.dp)
                    .border(width = 1.dp, color = Color.LightGray)
            )


            // Text Column
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_bio),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        ) { append("Jet Word ") }

                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontSize = MaterialTheme.typography.body1.fontSize,
                                fontWeight = MaterialTheme.typography.body1.fontWeight
                            )
                        ) { append(stringResource(id = R.string.app_info)) }
                    }
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        ) { append("E-mail: ") }

                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.body2.fontStyle,
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = MaterialTheme.typography.body2.fontWeight
                            )
                        ) { append("mahan.ziyari.work@gmail.com") }
                    },
                    modifier = Modifier.padding(top = 30.dp)
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        ) { append("Phone Number: ") }

                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.body2.fontStyle,
                                fontSize = MaterialTheme.typography.body2.fontSize,
                                fontWeight = MaterialTheme.typography.body2.fontWeight
                            )
                        ) { append("(+98)9912801655") }
                    },
                    modifier = Modifier.padding(top = 5.dp)
                )

            }// End of Description Column
        }
    }
}


@Preview
@Composable
private fun AboutScreenPreview() {
    AboutScreen {

    }
}
