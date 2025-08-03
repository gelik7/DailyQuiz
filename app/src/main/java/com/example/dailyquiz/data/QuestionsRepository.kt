package com.example.dailyquiz.data

object QuestionsRepository {
    val questions = listOf(
        Question(
            text = "Как переводится слово \"apple\"?",
            options = listOf("Груша", "Яблоко", "Апельсин", "Ананас"),
            correctAnswerIndex = 1
        ),
        Question(
            text = "Какое слово означает цвет?",
            options = listOf("Table", "Blue", "Run", "Chair"),
            correctAnswerIndex = 1
        ),
        Question(
            text = "Какое из слов — местоимение?",
            options = listOf("He", "Cat", "Big", "Go"),
            correctAnswerIndex = 0
        ),
        Question(
            text = "Как по-английски сказать: \"У меня есть книга\"?",
            options = listOf("I am book", "I was a book", "Big", "I has a book"),
            correctAnswerIndex = 3
        ),
        Question(
            text = "Выберите правильный перевод фразы \"Good morning\"?",
            options = listOf("Добрый вечер", "Привет", "Доброе утро", "Спокойной ночи"),
            correctAnswerIndex = 2
        )
    )
}
