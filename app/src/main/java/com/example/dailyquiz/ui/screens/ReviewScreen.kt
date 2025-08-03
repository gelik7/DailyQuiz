package com.example.dailyquiz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.data.Question

@Composable
fun ReviewScreen(
    questions: List<Question>,
    userAnswers: List<Int?>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7B61FF))
            .padding(16.dp)
    ) {
        Text(
            text = "Результаты",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(questions) { index, question ->
                val userAnswer = userAnswers.getOrNull(index)
                val isCorrect = userAnswer == question.correctAnswerIndex

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 4.dp,
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Вопрос ${index + 1}: ${question.text}",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        question.options.forEachIndexed { optIndex, option ->
                            val isUserAnswer = userAnswer == optIndex
                            val isCorrectAnswer = optIndex == question.correctAnswerIndex

                            val borderColor = when {
                                isCorrectAnswer -> Color(0xFF4CAF50)
                                isUserAnswer && !isCorrectAnswer -> Color(0xFFF44336)
                                else -> Color.LightGray
                            }

                            val icon = when {
                                isCorrectAnswer -> Icons.Default.CheckCircle
                                isUserAnswer && !isCorrectAnswer -> Icons.Default.Close
                                else -> null
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .border(
                                        width = 2.dp,
                                        color = borderColor,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 16.sp,
                                    modifier = Modifier.weight(1f)
                                )

                                if (icon != null) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = borderColor
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "НАЗАД",
                color = Color(0xFF7B61FF),
                fontSize = 16.sp
            )
        }
    }
}
