package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun HomeScreen(
    onPersonalityTestClick: () -> Unit,
    onLogicalTestClick: () -> Unit,
    onViewResultsClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "scaleAnim"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Personality & Shape Logic Game",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Button(
                onClick = onPersonalityTestClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Personality Test", fontSize = 18.sp)
            }
            Button(
                onClick = onLogicalTestClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Logical Shape Test", fontSize = 18.sp)
            }
            Button(
                onClick = onViewResultsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("View Scores", fontSize = 18.sp)
            }
        }

        // ✅ Banner Ad at the bottom
        BannerAdView()
    }
}

@Composable
fun BannerAdView() {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        factory = { context ->
            val adView = AdView(context)
            adView.setAdSize(AdSize.BANNER)
            adView.adUnitId = "ca-app-pub-2564748736569149/5734925790" // Replace with your actual unit ID
            adView.loadAd(AdRequest.Builder().build())
            adView
        }
    )
}

