package com.yodgorbek.mindshapes.data.source


import com.yodgorbek.mindshapes.domain.model.Question
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.domain.model.Shape

class LocalTestDataSource {
    fun getPersonalityQuestions(): List<Question> = listOf(
        Question(
            id = 1,
            text = "How do you lead a team under tight deadlines?",
            options = listOf(
                "Take charge and delegate tasks decisively",
                "Encourage team collaboration to meet goals",
                "Create a detailed plan to stay on track",
                "Seek guidance to ensure success"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Leadership"
        ),
        Question(
            id = 2,
            text = "How do you handle unexpected project changes?",
            options = listOf(
                "Adapt quickly and find creative solutions",
                "Consult colleagues to adjust plans",
                "Stick to the original plan if possible",
                "Evaluate all options carefully"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Adaptability"
        ),
        Question(
            id = 3,
            text = "What role do you prefer in a team?",
            options = listOf(
                "Leading and setting direction",
                "Collaborating and sharing ideas",
                "Supporting others’ tasks",
                "Working independently"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Teamwork"
        ),
        Question(
            id = 4,
            text = "How do you approach complex problems?",
            options = listOf(
                "Break them down systematically",
                "Brainstorm creative solutions",
                "Rely on past experiences",
                "Research extensively"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "ProblemSolving"
        ),
        Question(
            id = 5,
            text = "How do you communicate with stakeholders?",
            options = listOf(
                "Present clear, concise information",
                "Listen actively and incorporate feedback",
                "Use visuals and data to support points",
                "Build trust through relationships"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Communication"
        ),
        Question(
            id = 6,
            text = "How do you manage stress in high-pressure situations?",
            options = listOf(
                "Stay calm and focus on priorities",
                "Seek support from teammates",
                "Take breaks to recharge",
                "Analyze the situation methodically"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Resilience"
        ),
        Question(
            id = 7,
            text = "How do you approach innovation in your work?",
            options = listOf(
                "Propose bold, new ideas",
                "Refine existing processes",
                "Collaborate to spark creativity",
                "Test ideas cautiously"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Creativity"
        ),
        Question(
            id = 8,
            text = "How do you handle ethical dilemmas at work?",
            options = listOf(
                "Follow a strict moral code",
                "Seek team consensus",
                "Consult company policies",
                "Balance ethics with practicality"
            ),
            type = QuestionType.PERSONALITY,
            personalityTrait = "Ethics"
        )
    )

    fun getLogicalShapeQuestions(): List<Question> = listOf(
        Question(
            id = 1,
            text = "Which shape completes: Circle, Square, Circle, Square, ?",
            options = listOf("Circle", "Square", "Triangle", "Pentagon", "Star"),
            correctAnswerIndex = 2,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.CIRCLE, Shape.SQUARE)
        ),
        Question(
            id = 2,
            text = "What is next: Triangle, Circle, Triangle, Circle, ?",
            options = listOf("Square", "Triangle", "Circle", "Pentagon", "Star"),
            correctAnswerIndex = 1,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.TRIANGLE, Shape.CIRCLE, Shape.TRIANGLE, Shape.CIRCLE)
        ),
        Question(
            id = 3,
            text = "Complete: Square, Square, Triangle, Triangle, ?",
            options = listOf("Circle", "Square", "Triangle", "Pentagon", "Star"),
            correctAnswerIndex = 2,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.SQUARE, Shape.SQUARE, Shape.TRIANGLE, Shape.TRIANGLE)
        ),
        Question(
            id = 4,
            text = "Next shape: Circle, Triangle, Square, Circle, ?",
            options = listOf("Triangle", "Square", "Circle", "Pentagon", "Star"),
            correctAnswerIndex = 0,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.CIRCLE, Shape.TRIANGLE, Shape.SQUARE, Shape.CIRCLE)
        ),
        Question(
            id = 5,
            text = "Identify next: Triangle, Square, Circle, Triangle, ?",
            options = listOf("Square", "Circle", "Triangle", "Pentagon", "Star"),
            correctAnswerIndex = 0,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.TRIANGLE, Shape.SQUARE, Shape.CIRCLE, Shape.TRIANGLE)
        ),
        Question(
            id = 6,
            text = "What follows: Pentagon, Star, Pentagon, Star, ?",
            options = listOf("Circle", "Square", "Triangle", "Pentagon", "Star"),
            correctAnswerIndex = 3,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.PENTAGON, Shape.STAR, Shape.PENTAGON, Shape.STAR)
        ),
        Question(
            id = 7,
            text = "Next in: Circle, Pentagon, Square, Circle, ?",
            options = listOf("Triangle", "Square", "Pentagon", "Star", "Circle"),
            correctAnswerIndex = 2,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.CIRCLE, Shape.PENTAGON, Shape.SQUARE, Shape.CIRCLE)
        ),
        Question(
            id = 8,
            text = "Complete: Star, Triangle, Circle, Star, ?",
            options = listOf("Circle", "Square", "Triangle", "Pentagon", "Star"),
            correctAnswerIndex = 0,
            type = QuestionType.LOGICAL,
            shapeSequence = listOf(Shape.STAR, Shape.TRIANGLE, Shape.CIRCLE, Shape.STAR)
        )
    )
}