package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.presentation.TestUiState

import com.yodgorbek.mindshapes.presentation.TestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: TestViewModel = koinViewModel()
) {
    var userName by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPersonalityTestClick = {
                    viewModel.loadQuestions(QuestionType.PERSONALITY)
                    navController.navigate("test")
                },
                onLogicalTestClick = {
                    viewModel.loadQuestions(QuestionType.LOGICAL)
                    navController.navigate("test")
                },
                onViewResultsClick = {
                    viewModel.loadResults()
                    navController.navigate("results")
                }
            )
        }
        composable("test") {
            when (val state = uiState) {
                is TestUiState.Success -> TestScreen(
                    questions = state.questions,
                    testType = state.testType,
                    userName = userName,
                    onUserNameChange = { userName = it },
                    onSubmit = { score, personalityType ->
                        viewModel.submitTest(userName, state.testType, score, personalityType)
                        navController.navigate("result_saved")
                    }
                )
                is TestUiState.Loading -> LoadingScreen()
                is TestUiState.Error -> ErrorScreen(state.message) {
                    navController.navigate("home")
                }
                else -> {
                    navController.navigate("home")
                }
            }
        }
        composable("result_saved") {
            when (val state = uiState) {
                is TestUiState.ResultSaved -> ResultSavedScreen(
                    score = state.score,
                    personalityType = state.personalityType,
                    onRestart = { navController.navigate("home") }
                )
                else -> {
                    navController.navigate("home")
                }
            }
        }
        composable("results") {
            when (val state = uiState) {
                is TestUiState.ResultsLoaded -> ResultsScreen(
                    results = state.results,
                    onBack = { navController.navigate("home") }
                )
                is TestUiState.Loading -> LoadingScreen()
                is TestUiState.Error -> ErrorScreen(state.message) {
                    navController.navigate("home")
                }
                else -> {
                    navController.navigate("home")
                }
            }
        }
    }
}