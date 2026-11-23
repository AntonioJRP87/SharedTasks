package com.antonio.sharedtasks

import android.app.Application
import com.antonio.sharedtasks.di.initKoin
import org.koin.android.ext.koin.androidContext

class SharedTasksApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@SharedTasksApp)
        }
    }
}