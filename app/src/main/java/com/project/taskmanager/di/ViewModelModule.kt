package com.project.taskmanager.di

import com.project.taskmanager.ui.feature.home.TaskHomeViewModel
import com.project.taskmanager.ui.feature.taskDetails.AddTaskViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { TaskHomeViewModel(get()) }
    viewModel { AddTaskViewModel(get()) }
}