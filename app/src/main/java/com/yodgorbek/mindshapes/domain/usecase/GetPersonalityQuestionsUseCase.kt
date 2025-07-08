package com.yodgorbek.mindshapes.domain.usecase

import com.yodgorbek.mindshapes.domain.model.Question
import com.yodgorbek.mindshapes.domain.repository.TestRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPersonalityQuestionsUseCase : KoinComponent {
    private val repository: TestRepository by inject()
    suspend operator fun invoke(): List<Question> = repository.getPersonalityQuestions()
}