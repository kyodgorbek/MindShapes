package com.yodgorbek.mindshapes.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_results")
data class TestResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userName: String,
    val testType: String,
    val score: Int,
    val personalityType: String?,
    val timestamp: Long
)