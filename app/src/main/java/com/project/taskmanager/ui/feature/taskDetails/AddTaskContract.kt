package com.project.taskmanager.ui.feature.taskDetails

import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.base.ViewEvent
import com.project.taskmanager.ui.base.ViewSideEffect
import com.project.taskmanager.ui.base.ViewState

class AddTaskContract {

    sealed class Event : ViewEvent {
        data class OnAddTaskClicked(val task: TaskModel) : Event()
        data class OnTaskTitleChanged(val title: String) : Event()
        data class OnTaskDescriptionChanged(val description: String) : Event()
        data object ShowDatePicker : Event()
        data object ShowTimePicker : Event()
        data class OnDateSelected(val date: String) : Event()
        data class OnTimeSelected(val time: String) : Event()
        data class OnUpdateTask(val task: TaskModel) : Event()
    }

    data class State(
        val taskTitle: String,
        val taskDescription: String,
        val date: String,
        val time: String,
        val priority: String,
        val showDatePicker: Boolean,
        val showTimePicker: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class ShowToast(val message: String) : Navigation()

        sealed class Navigation : Effect() {

            data object BackToHome : Navigation()

        }
    }
}