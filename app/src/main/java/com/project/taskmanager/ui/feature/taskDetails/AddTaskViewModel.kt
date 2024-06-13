package com.project.taskmanager.ui.feature.taskDetails

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.project.taskmanager.common.orDefault
import com.project.taskmanager.common.utils.currentDateFormatted
import com.project.taskmanager.common.utils.getCurrentTimeFormatted
import com.project.taskmanager.data.TaskRepository
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.data.model.isNotNull
import com.project.taskmanager.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AddTaskViewModel(private val taskRepository: TaskRepository) :
    BaseViewModel<AddTaskContract.Event, AddTaskContract.State, AddTaskContract.Effect>() {

    override fun setInitialState() = AddTaskContract.State(
        taskTitle = "",
        taskDescription = "",
        date = "",
        time = "",
        priority = "",
        showDatePicker = false,
        showTimePicker = false
    )

    override fun handleEvents(event: AddTaskContract.Event) {
        when (event) {
            is AddTaskContract.Event.OnAddTaskClicked -> {
                val taskModel = TaskModel(
                    title = event.task.title,
                    description = event.task.description,
                    time = event.task.time,
                    date = viewState.value.date,
                    priority = viewState.value.priority
                )
                insertTaskToDatabase(taskModel)
            }

            is AddTaskContract.Event.OnTaskDescriptionChanged -> {
                setState {
                    copy(
                        taskDescription = event.description
                    )
                }
            }

            is AddTaskContract.Event.OnTaskTitleChanged -> {
                setState {
                    copy(
                        taskTitle = event.title
                    )
                }
            }

            AddTaskContract.Event.ShowDatePicker -> {
                setState {
                    copy(
                        showDatePicker = true
                    )
                }
            }

            AddTaskContract.Event.ShowTimePicker -> {
                setState {
                    copy(
                        showTimePicker = true
                    )
                }
            }

            is AddTaskContract.Event.OnDateSelected -> {
                setState {
                    copy(
                        date = event.date,
                        showDatePicker = false
                    )
                }
            }


            is AddTaskContract.Event.OnTimeSelected -> {
                setState {
                    copy(
                        time = event.time,
                        showTimePicker = false
                    )
                }
            }

            is AddTaskContract.Event.OnUpdateTask -> {
                updateTask(event.task)
            }
        }
    }

    private fun insertTaskToDatabase(taskModel: TaskModel) {
        viewModelScope.launch {
            if (taskModel.isNotNull()) {
                taskRepository.insertTask(taskModel)
                    .onSuccess {
                        setEffect { AddTaskContract.Effect.ShowToast("Task Added Successfully") }
                        setEffect { AddTaskContract.Effect.Navigation.BackToHome }
                    }
                    .onFailure {
                        setEffect { AddTaskContract.Effect.ShowToast(it.message.toString()) }
                    }
            } else {
                setEffect { AddTaskContract.Effect.ShowToast("Please check the required fields") }
            }
        }
    }

    fun getTaskDetailByID(taskID: String) {
        val taskIdInt = taskID.toIntOrNull()
        if (taskIdInt != null && taskIdInt != -1) {
            viewModelScope.launch {
                taskRepository.getTaskByID(taskId = taskIdInt)
                    .onSuccess { it ->
                        it.let { task ->
                            setState {
                                copy(
                                    taskTitle = task.title ?: "",
                                    taskDescription = task.description ?: "",
                                    date = task.date ?: "",
                                    time = task.time ?: "",
                                    priority = task.priority ?: ""
                                )
                            }
                        }
                    }
                    .onFailure {
                        Log.d("TaskStatus", "Failed to get task detail")
                    }
            }
        }
    }

    private fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
                .onSuccess {
                    setEffect { AddTaskContract.Effect.ShowToast("Task Updated Successfully") }
                    setEffect { AddTaskContract.Effect.Navigation.BackToHome }
                }
                .onFailure {
                    setEffect { AddTaskContract.Effect.ShowToast(it.message.toString()) }
                }
        }
    }


}