package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.presentation.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: TestViewModel = koinViewModel()
) {
    var userName by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    // 🔁 Handle navigation side-effects
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                UiEvent.NavigateToTest -> navController.navigate("test")
                UiEvent.NavigateToResults -> navController.navigate("results")
                UiEvent.NavigateToResultSaved -> navController.navigate("result_saved")
                UiEvent.NavigateToHome -> navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
                is UiEvent.ShowError -> {
                    println("Error: ${event.message}")
                    navController.navigate("home")
                }
            }
        }
    }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPersonalityTestClick = { viewModel.loadQuestions(QuestionType.PERSONALITY) },
                onLogicalTestClick = { viewModel.loadQuestions(QuestionType.LOGICAL) },
                onViewResultsClick = { viewModel.loadResults() }
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
                    }
                )
                is TestUiState.Loading -> LoadingScreen()
                is TestUiState.Error -> ErrorScreen(state.message) {
                    viewModel.clearUiState()
                }
                else -> Unit
            }
        }

        composable("result_saved") {
            val state = uiState
            if (state is TestUiState.ResultSaved) {
                ResultSavedScreen(
                    score = state.score,
                    personalityType = state.personalityType,
                    onRestart = {
                        viewModel.clearUiState()
                        navController.navigate("home")
                    }
                )
            }
        }

        composable("results") {
            when (val state = uiState) {
                is TestUiState.ResultsLoaded -> ResultsScreen(
                    results = state.results,
                    onBack = {
                        viewModel.clearUiState()
                        navController.navigate("home")
                    }
                )
                is TestUiState.Loading -> LoadingScreen()
                is TestUiState.Error -> ErrorScreen(state.message) {
                    viewModel.clearUiState()
                }
                else -> Unit
            }
        }
    }
}
