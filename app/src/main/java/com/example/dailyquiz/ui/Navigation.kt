package com.example.dailyquiz.ui

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dailyquiz.data.Question
import com.example.dailyquiz.ui.screens.*

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Quiz : Screen("quiz")
    object History : Screen("history")
    object Result : Screen("result/{correctAnswers}/{totalQuestions}") {
        fun createRoute(correctAnswers: Int, totalQuestions: Int): String {
            return "result/$correctAnswers/$totalQuestions"
        }
    }
    object Review : Screen("review")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val questions = remember {
        listOf(
            Question("Как переводится слово \"apple\"?", listOf("Груша", "Яблоко", "Апельсин", "Ананас"), 1),
            Question("Какое слово означает цвет?", listOf("Table", "Blue", "Run", "Chair"), 1),
            Question("Какое из слов — местоимение?", listOf("He", "Cat", "Big", "Go"), 0),
            Question("Как по-английски сказать: \"У меня есть книга\"?", listOf("I am book", "I was a book", "Big", "I has a book"), 3),
            Question("Выберите правильный перевод фразы \"Good morning\"?", listOf("Добрый вечер", "Привет", "Доброе утро", "Спокойной ночи"), 2)
        )
    }

    val userAnswers = remember { mutableStateListOf<Int?>() }

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            userAnswers.clear()
            WelcomeScreen(
                onStartClick = {
                    navController.navigate(Screen.Quiz.route)
                },
                onHistoryClick = {
                    navController.navigate(Screen.History.route)
                }
            )
        }

        composable(Screen.Quiz.route) {
            var currentIndex by remember { mutableStateOf(0) }
            var correctAnswersCount by remember { mutableStateOf(0) }
            val totalQuestions = questions.size
            val currentQuestion = questions[currentIndex]

            QuizScreen(
                question = currentQuestion,
                questionIndex = currentIndex,
                totalQuestions = totalQuestions,
                onAnswerSelected = { selectedIndex ->
                    if (userAnswers.size <= currentIndex) {
                        userAnswers.add(selectedIndex)
                    } else {
                        userAnswers[currentIndex] = selectedIndex
                    }

                    if (selectedIndex == currentQuestion.correctAnswerIndex) {
                        correctAnswersCount++
                    }
                },
                onNextClicked = {
                    if (currentIndex < totalQuestions - 1) {
                        currentIndex++
                    } else {
                        navController.navigate(
                            Screen.Result.createRoute(
                                correctAnswersCount,
                                totalQuestions
                            )
                        )
                    }
                }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(
            route = Screen.Result.route,
            arguments = listOf(
                navArgument("correctAnswers") { type = NavType.IntType },
                navArgument("totalQuestions") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0

            ResultScreen(
                correctAnswers = correctAnswers,
                totalQuestions = totalQuestions,
                onRestart = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onReview = {
                    navController.navigate(Screen.Review.route)
                }
            )
        }

        composable(Screen.Review.route) {
            ReviewScreen(
                questions = questions,
                userAnswers = userAnswers,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
