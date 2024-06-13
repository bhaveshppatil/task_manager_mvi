package com.project.taskmanager.di

import android.app.Application
import androidx.room.Room
import com.project.taskmanager.db.TaskDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideTaskDao(get()) }
}

fun provideDatabase(application: Application): TaskDatabase =
    Room.databaseBuilder(
        application,
        TaskDatabase::class.java,
        "task_database"
    )
        .fallbackToDestructiveMigration()
        .build()

fun provideTaskDao(database: TaskDatabase) = database.getTaskDAO()