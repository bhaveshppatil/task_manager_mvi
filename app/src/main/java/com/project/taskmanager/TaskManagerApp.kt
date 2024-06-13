package com.project.taskmanager

import android.app.Application
import com.project.taskmanager.di.databaseModule
import com.project.taskmanager.di.repositoryModule
import com.project.taskmanager.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TaskManagerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@TaskManagerApp)
            modules(listOf(databaseModule, viewModelModule, repositoryModule))
        }
    }
}