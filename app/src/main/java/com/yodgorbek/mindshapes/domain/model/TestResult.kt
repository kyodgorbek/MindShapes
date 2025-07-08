package com.yodgorbek.mindshapes.domain.model

data class TestResult(
    val id: Long = 0,
    val userName: String,
    val testType: QuestionType,
    val score: Int,
    val personalityType: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)