package com.yodgorbek.mindshapes.domain.model
import com.yodgorbek.mindshapes.domain.model.QuestionType
data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int? = null,
    val type: QuestionType,
    val shapeSequence: List<Shape>? = null,
    val personalityTrait: String? = null
)