package com.yodgorbek.mindshapes.domain.repository


import com.yodgorbek.mindshapes.domain.model.Question
import com.yodgorbek.mindshapes.domain.model.TestResult

interface TestRepository {
    suspend fun getPersonalityQuestions(): List<Question>
    suspend fun getLogicalShapeQuestions(): List<Question>
    suspend fun saveTestResult(result: TestResult)
    suspend fun getTestResults(): List<TestResult>
}