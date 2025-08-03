package com.example.dailyquiz.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(
    correctAnswers: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    onReview: () -> Unit
) {
    val backgroundColor = Color(0xFF7B61FF)
    val white = Color.White
    val purple = Color(0xFF7B61FF)

    val (title, subtitle) = when (correctAnswers) {
        5 -> "Идеально!" to "5/5 — вы ответили на всё правильно. Это блестящий результат!"
        4 -> "Почти идеально!" to "4/5 — очень близко к совершенству. Ещё один шаг!"
        3 -> "Хороший результат!" to "3/5 — вы на верном пути. Продолжайте тренироваться!"
        2 -> "Есть над чем поработать" to "2/5 — не расстраивайтесь, попробуйте ещё раз!"
        1 -> "Сложный вопрос?" to "1/5 — иногда просто не ваш день. Следующая попытка будет лучше!"
        else -> "Бывает и так!" to "0/5 — не отчаивайтесь. Начните заново и удивите себя!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Результаты",
            color = white,
            fontSize = 32.sp,
            style = MaterialTheme.typography.h5
        )

        Spacer(modifier = Modifier.height(24.dp))


        Row {
            repeat(5) { index ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (index < correctAnswers) Color.Yellow else Color.LightGray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = title,
            color = white,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subtitle,
            color = white,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(32.dp))


        Button(
            onClick = onRestart,
            colors = ButtonDefaults.buttonColors(backgroundColor = white),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "НАЧАТЬ ЗАНОВО",
                color = purple,
                fontSize = 16.sp,
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onReview,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color.White),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "РАЗОБРАТЬ ВИКТОРИНУ",
                color = Color.White,
                fontSize = 16.sp,
                style = MaterialTheme.typography.button
            )
        }
    }
}
