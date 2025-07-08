package com.yodgorbek.mindshapes.domain.usecase

import com.yodgorbek.mindshapes.domain.model.TestResult
import com.yodgorbek.mindshapes.domain.repository.TestRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveTestResultUseCase : KoinComponent {
    private val repository: TestRepository by inject()
    suspend operator fun invoke(result: TestResult) = repository.saveTestResult(result)
}