package com.yodgorbek.mindshapes.presentation

import com.yodgorbek.mindshapes.domain.model.Question
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.domain.model.TestResult


sealed class TestUiState {
    object Initial : TestUiState()
    object Loading : TestUiState()
    data class Success(val questions: List<Question>, val testType: QuestionType) : TestUiState()
    data class ResultSaved(val score: Int, val personalityType: String?) : TestUiState()
    data class ResultsLoaded(val results: List<TestResult>) : TestUiState()
    data class Error(val message: String) : TestUiState()
}