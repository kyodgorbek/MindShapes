package com.yodgorbek.mindshapes.data.repository


import com.yodgorbek.mindshapes.data.db.TestResultDao
import com.yodgorbek.mindshapes.data.db.TestResultEntity
import com.yodgorbek.mindshapes.data.source.LocalTestDataSource
import com.yodgorbek.mindshapes.domain.model.Question
import com.yodgorbek.mindshapes.domain.model.QuestionType
import com.yodgorbek.mindshapes.domain.model.TestResult
import com.yodgorbek.mindshapes.domain.repository.TestRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class TestRepositoryImpl : TestRepository, KoinComponent {
    private val localDataSource: LocalTestDataSource by inject()
    private val testResultDao: TestResultDao by inject()

    override suspend fun getPersonalityQuestions(): List<Question> =
        localDataSource.getPersonalityQuestions()

    override suspend fun getLogicalShapeQuestions(): List<Question> =
        localDataSource.getLogicalShapeQuestions()

    override suspend fun saveTestResult(result: TestResult) {
        testResultDao.insert(
            TestResultEntity(
                userName = result.userName,
                testType = result.testType.name,
                score = result.score,
                personalityType = result.personalityType,
                timestamp = result.timestamp
            )
        )
    }

    override suspend fun getTestResults(): List<TestResult> =
        testResultDao.getAllResults().map {
            TestResult(
                id = it.id,
                userName = it.userName,
                testType = QuestionType.valueOf(it.testType),
                score = it.score,
                personalityType = it.personalityType,
                timestamp = it.timestamp
            )
        }
}