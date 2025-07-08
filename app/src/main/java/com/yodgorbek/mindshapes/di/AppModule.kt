package com.yodgorbek.mindshapes.di



import android.content.Context
import androidx.room.Room
import com.yodgorbek.mindshapes.data.db.AppDatabase
import com.yodgorbek.mindshapes.data.repository.TestRepositoryImpl
import com.yodgorbek.mindshapes.data.source.LocalTestDataSource
import com.yodgorbek.mindshapes.domain.repository.TestRepository
import com.yodgorbek.mindshapes.domain.usecase.GetLogicalShapeQuestionsUseCase
import com.yodgorbek.mindshapes.domain.usecase.GetPersonalityQuestionsUseCase
import com.yodgorbek.mindshapes.domain.usecase.GetTestResultsUseCase
import com.yodgorbek.mindshapes.domain.usecase.SaveTestResultUseCase
import com.yodgorbek.mindshapes.presentation.TestViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LocalTestDataSource() }
    single<TestRepository> { TestRepositoryImpl() }
    single { GetPersonalityQuestionsUseCase() }
    single { GetLogicalShapeQuestionsUseCase() }
    single { SaveTestResultUseCase() }
    single { GetTestResultsUseCase() }
    viewModel { TestViewModel() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "test_database"
        ).build()
    }
    single { get<AppDatabase>().testResultDao() }
}