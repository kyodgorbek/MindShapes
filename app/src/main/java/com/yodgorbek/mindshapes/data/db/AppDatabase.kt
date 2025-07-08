package com.yodgorbek.mindshapes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TestResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testResultDao(): TestResultDao
}