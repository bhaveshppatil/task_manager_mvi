package com.project.taskmanager.ui.feature.home

import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.base.ViewEvent
import com.project.taskmanager.ui.base.ViewSideEffect
import com.project.taskmanager.ui.base.ViewState
import com.project.taskmanager.ui.feature.taskDetails.AddTaskContract


class HomeScreenContract {

    sealed class Event : ViewEvent {
        data object OnNewTaskClick : Event()
        data class OnTaskClick(val taskID: String) : Event()
        data class OnEditClick(val taskID: String) : Event()
        data class OnDeleteClick(val task: TaskModel) : Event()
        data class OnCompletedClick(val task: TaskModel) : Event()
    }

    data class State(
        val tasks: List<TaskModel>,
        val completedTasks: List<TaskModel>,
        val inProgressTasks: List<TaskModel>,
        val isTaskListEmpty: Boolean,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {

        data class ShowToast(val message: String) : Navigation()

        sealed class Navigation : Effect() {

            data object NavigateToNewTask : Navigation()

            data class NavigateToTaskDetail(val taskID: String) : Navigation()
        }
    }
}
