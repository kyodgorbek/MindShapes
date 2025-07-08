package com.yodgorbek.mindshapes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.domain.model.TestResult
import com.yodgorbek.mindshapes.domain.usecase.GetLogicalShapeQuestionsUseCase
import com.yodgorbek.mindshapes.domain.usecase.GetPersonalityQuestionsUseCase
import com.yodgorbek.mindshapes.domain.usecase.GetTestResultsUseCase
import com.yodgorbek.mindshapes.domain.usecase.SaveTestResultUseCase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TestViewModel : ViewModel(), KoinComponent {
    private val getPersonalityQuestions: GetPersonalityQuestionsUseCase by inject()
    private val getLogicalShapeQuestions: GetLogicalShapeQuestionsUseCase by inject()
    private val saveTestResult: SaveTestResultUseCase by inject()
    private val getTestResults: GetTestResultsUseCase by inject()

    private val _uiState = MutableStateFlow<TestUiState>(TestUiState.Initial)
    val uiState: StateFlow<TestUiState> = _uiState.asStateFlow()

    fun loadQuestions(testType: QuestionType) {
        viewModelScope.launch {
            _uiState.value = TestUiState.Loading
            try {
                val questions = when (testType) {
                    QuestionType.PERSONALITY -> getPersonalityQuestions()
                    QuestionType.LOGICAL -> getLogicalShapeQuestions()
                }
                _uiState.value = TestUiState.Success(questions, testType)
            } catch (e: Exception) {
                _uiState.value = TestUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun submitTest(userName: String, testType: QuestionType, score: Int, personalityType: String? = null) {
        viewModelScope.launch {
            saveTestResult(
                TestResult(
                    userName = userName,
                    testType = testType,
                    score = score,
                    personalityType = personalityType
                )
            )
            _uiState.value = TestUiState.ResultSaved(score, personalityType)
        }
    }

    fun loadResults() {
        viewModelScope.launch {
            _uiState.value = TestUiState.Loading
            try {
                val results = getTestResults()
                _uiState.value = TestUiState.ResultsLoaded(results)
            } catch (e: Exception) {
                _uiState.value = TestUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}