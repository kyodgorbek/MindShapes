package com.yodgorbek.mindshapes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TestResultDao {
    @Insert
    suspend fun insert(result: TestResultEntity)

    @Query("SELECT * FROM test_results ORDER BY timestamp DESC")
    suspend fun getAllResults(): List<TestResultEntity>
}