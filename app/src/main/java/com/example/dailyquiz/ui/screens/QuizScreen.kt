package com.example.dailyquiz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.data.Question

@Composable
fun QuizScreen(
    question: Question,
    questionIndex: Int,
    totalQuestions: Int,
    onAnswerSelected: (Int) -> Unit,
    onNextClicked: () -> Unit
) {
    val selectedOptionIndex = remember { mutableStateOf<Int?>(null) }
    val isLastQuestion = questionIndex == totalQuestions - 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF7B61FF))
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "DAILYQUIZ",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )


        Text(
            text = "Вопрос ${questionIndex + 1} из $totalQuestions",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = question.text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                question.options.forEachIndexed { index, option ->
                    val isSelected = selectedOptionIndex.value == index
                    val borderColor = if (isSelected) Color(0xFF7B61FF) else Color.LightGray

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
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .padding(12.dp)
                            .clickable { selectedOptionIndex.value = index }
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedOptionIndex.value = index },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF7B61FF),
                                unselectedColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = option, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Вернуться к предыдущим вопросам нельзя",
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = {
                selectedOptionIndex.value?.let {
                    onAnswerSelected(it)
                    onNextClicked()
                }
            },
            enabled = selectedOptionIndex.value != null,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = if (isLastQuestion) "ЗАВЕРШИТЬ" else "ДАЛЕЕ",
                color = Color(0xFF7B61FF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
