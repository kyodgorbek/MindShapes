package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ResultSavedScreen(score: Int, personalityType: String?, onRestart: () -> Unit) {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000), label = "ResultAlpha"
    )

    // Delay before navigating back to home
    LaunchedEffect(Unit) {
        delay(1500)
        onRestart()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Result Saved! Score: $score",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Green.copy(alpha = alpha),
                fontWeight = FontWeight.Bold
            )

            personalityType?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Personality: $it",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Green.copy(alpha = alpha),
                    fontSize = 18.sp
                )
            }
        }
    }
}
