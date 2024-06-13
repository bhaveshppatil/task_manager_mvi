package com.project.taskmanager.ui.feature.home

import androidx.lifecycle.viewModelScope
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.base.BaseViewModel
import kotlinx.coroutines.launch


import com.project.taskmanager.data.TaskRepository

class TaskHomeViewModel(private val taskRepository: TaskRepository) :
    BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>() {

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            taskRepository.getAllTasks().let { result ->
                result.onSuccess { tasks ->
                    setState {
                        copy(
                            tasks = tasks,
                            isTaskListEmpty = tasks.isEmpty(),
                            isLoading = false,
                            isError = false
                        )
                    }
                }.onFailure {
                    setState {
                        copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
            }
        }
    }

    override fun setInitialState() = HomeScreenContract.State(
        tasks = emptyList(),
        completedTasks = emptyList(),
        inProgressTasks = emptyList(),
        isTaskListEmpty = true,
        isLoading = true,
        isError = false,
    )

    override fun handleEvents(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.OnTaskClick -> {
                setEffect {
                    HomeScreenContract.Effect.Navigation.NavigateToTaskDetail(event.taskID)
                }
            }

            is HomeScreenContract.Event.OnCompletedClick -> {
                event.task.id?.let { markTaskAsCompleted(it) }
            }

            is HomeScreenContract.Event.OnDeleteClick -> {
                event.task.id?.let { deleteTaskById(it) }
            }

            is HomeScreenContract.Event.OnEditClick -> {
                setEffect {
                    HomeScreenContract.Effect.Navigation.NavigateToTaskDetail(event.taskID)
                }
            }

            HomeScreenContract.Event.OnNewTaskClick -> {
                setEffect {
                    HomeScreenContract.Effect.Navigation.NavigateToNewTask
                }
            }

        }
    }

    private fun markTaskAsCompleted(taskId: Int) {
        viewModelScope.launch {
            taskRepository.markTaskAsCompleted(taskId)
                .onSuccess {
                    setEffect { HomeScreenContract.Effect.ShowToast("Task Completed Successfully") }
                }
                .onFailure {
                    setEffect { HomeScreenContract.Effect.ShowToast("Failed to mark task as completed") }
                }
        }
    }

    private fun deleteTaskById(taskId: Int) {
        viewModelScope.launch {
            taskRepository.deleteTaskById(taskId)
                .onSuccess {
                    setEffect { HomeScreenContract.Effect.ShowToast("Task Deleted Successfully") }
                    fetchTasks()
                }
                .onFailure {
                    setEffect { HomeScreenContract.Effect.ShowToast("Failed to delete task") }
                }
        }
    }

    fun searchTaskInDatabase(searchQuery: String) {
        viewModelScope.launch {
            taskRepository.searchTaskInDatabase(searchQuery).let { result ->
                // Handle result if needed
            }
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            taskRepository.deleteAllTasks().let { result ->
                // Handle result if needed
            }
        }
    }
}
