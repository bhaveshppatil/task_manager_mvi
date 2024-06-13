package com.project.taskmanager.di

import com.project.taskmanager.data.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TaskRepository(get()) }

}