package com.example.dailyquiz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onStartClick: () -> Unit,
    onHistoryClick: () -> Unit,
    isError: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7B61FF))
            .padding(24.dp)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 32.dp)
        ) {
            Button(
                onClick = onHistoryClick,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "История",
                    color = Color(0xFF7B61FF),
                    fontSize = 14.sp
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DAILYQUIZ",
                color = Color.White,
                fontSize = 32.sp,
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Добро пожаловать в DailyQuiz!",
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onStartClick,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B61FF)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "НАЧАТЬ ВИКТОРИНУ",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    if (isError) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Ошибка! Попробуйте ещё раз.",
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
