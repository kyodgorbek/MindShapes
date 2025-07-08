package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ResultSavedScreen(score: Int, personalityType: String?, onRestart: () -> Unit) {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(Unit) {
        delay(1500)
        onRestart()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Result Saved! Score: $score",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Green.copy(alpha = alpha),
                fontWeight = FontWeight.Bold
            )
            if (personalityType != null) {
                Text(
                    text = "Personality: $personalityType",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Green.copy(alpha = alpha),
                    fontSize = 18.sp
                )
            }
        }
    }
}