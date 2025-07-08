package com.yodgorbek.mindshapes

import android.app.Application
import com.yodgorbek.mindshapes.di.appModule
import com.yodgorbek.mindshapes.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp:Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule, databaseModule)
        }

    }

}