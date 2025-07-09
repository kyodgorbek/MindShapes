package com.yodgorbek.mindshapes.presentation.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yodgorbek.mindshapes.domain.model.Question
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.domain.model.Shape
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    questions: List<Question>,
    testType: QuestionType,
    userName: String,
    onUserNameChange: (String) -> Unit,
    onSubmit: (Int, String?) -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    val selectedAnswers = remember { mutableStateMapOf<Int, Int>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]

            Text(
                text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.text,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (testType == QuestionType.LOGICAL && question.shapeSequence != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    question.shapeSequence.forEach { shape ->
                        ShapeIcon(shape = shape, modifier = Modifier.size(40.dp))
                    }
                }
            }

            question.options.forEachIndexed { index, option ->
                val isSelected = selectedAnswers[question.id] == index
                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1.1f else 1f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
                    label = "AnswerScale"
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            selectedAnswers[question.id] = index
                        }
                        .scale(scale),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0xFFBBDEFB) else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = {
                                selectedAnswers[question.id] = index
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (testType == QuestionType.LOGICAL &&
                        selectedAnswers[question.id] == question.correctAnswerIndex
                    ) {
                        score++
                    }
                    currentQuestionIndex++
                },
                enabled = selectedAnswers.containsKey(question.id),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Next")
            }
        } else {
            val updatedTraitScores = remember {
                val result = mutableMapOf<String, Int>()
                questions.forEach { question ->
                    if (testType == QuestionType.PERSONALITY && question.personalityTrait != null) {
                        val selected = selectedAnswers[question.id]
                        val points = if (selected == 0) 2 else 1
                        result[question.personalityTrait!!] =
                            result.getOrDefault(question.personalityTrait!!, 0) + points
                    }
                }
                result
            }

            val updatedPersonalityType = when (updatedTraitScores.maxByOrNull { it.value }?.key) {
                "Leadership" -> "Decisive Leader"
                "Adaptability" -> "Adaptive Innovator"
                "Teamwork" -> "Collaborative Team Player"
                "ProblemSolving" -> "Strategic Problem Solver"
                "Communication" -> "Effective Communicator"
                "Resilience" -> "Resilient Achiever"
                "Creativity" -> "Creative Visionary"
                "Ethics" -> "Ethical Decision Maker"
                else -> "Balanced Contributor"
            }

            if (testType == QuestionType.LOGICAL) {
                Text(
                    text = "Your Score: $score / ${questions.size}",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            } else {
                Text(
                    text = "Your Personality Type: $updatedPersonalityType",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                updatedTraitScores.forEach { (trait, value) ->
                    Text("$trait: $value points")
                }
            }

            Button(
                onClick = {
                    val finalScore = if (testType == QuestionType.LOGICAL) score
                    else updatedTraitScores.values.sum()
                    val personalityType = if (testType == QuestionType.PERSONALITY) updatedPersonalityType else null
                    onSubmit(finalScore, personalityType)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Test")
            }
        }
    }
}

@Composable
fun ShapeIcon(shape: Shape, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        when (shape) {
            Shape.CIRCLE -> drawCircle(color = Color.Blue, radius = size.minDimension / 2)
            Shape.SQUARE -> drawRect(color = Color.Red, size = size)
            Shape.TRIANGLE -> drawPath(
                path = Path().apply {
                    moveTo(size.width / 2, 0f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                },
                color = Color.Green
            )
            Shape.PENTAGON -> drawPath(
                path = Path().apply {
                    val radius = size.minDimension / 2
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    (0..4).forEach { i ->
                        val angle = Math.toRadians((90 + i * 72).toDouble()).toFloat()
                        val x = centerX + radius * cos(angle)
                        val y = centerY - radius * sin(angle)
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                },
                color = Color.Magenta
            )
            Shape.STAR -> drawPath(
                path = Path().apply {
                    val outerRadius = size.minDimension / 2
                    val innerRadius = outerRadius / 2
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    (0..9).forEach { i ->
                        val radius = if (i % 2 == 0) outerRadius else innerRadius
                        val angle = Math.toRadians((90 + i * 36).toDouble()).toFloat()
                        val x = centerX + radius * cos(angle)
                        val y = centerY - radius * sin(angle)
                        if (i == 0) moveTo(x, y) else lineTo(x, y)
                    }
                    close()
                },
                color = Color.Yellow
            )
        }
    }
}
