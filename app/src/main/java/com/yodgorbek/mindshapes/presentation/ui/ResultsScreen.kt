package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yodgorbek.mindshapes.domain.model.TestResult
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ResultsScreen(results: List<TestResult>, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Scoreboard",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(results) { result ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Name: ${result.userName}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "Test: ${result.testType}", fontSize = 14.sp)
                        Text(text = "Score: ${result.score}", fontSize = 14.sp)
                        result.personalityType?.let {
                            Text(text = "Personality: $it", fontSize = 14.sp)
                        }
                        Text(
                            text = "Date: ${
                                SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                                    .format(Date(result.timestamp))
                            }",
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Home")
        }
    }
}
