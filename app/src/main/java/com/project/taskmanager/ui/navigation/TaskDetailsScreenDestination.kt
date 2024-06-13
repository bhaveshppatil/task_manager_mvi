package com.project.taskmanager.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.taskmanager.common.MyDatePickerDialog
import com.project.taskmanager.common.TimePickerDialog
import com.project.taskmanager.common.toastMessage
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.base.SIDE_EFFECTS_KEY
import com.project.taskmanager.ui.feature.taskDetails.AddTaskContract
import com.project.taskmanager.ui.feature.taskDetails.AddTaskViewModel
import com.project.taskmanager.ui.feature.taskDetails.composables.AddNewTaskScreen
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel
import java.util.Calendar

@Composable
fun TaskDetailScreenDestination(taskID: String, navController: NavController) {
    val viewModel = getViewModel<AddTaskViewModel>()

    LaunchedEffect(key1 = true) {
        viewModel.getTaskDetailByID(taskID)
    }

    DetailScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        navigateBackToHome = {
            navController.navigateToHomeScreen()
        }
    )
}

@Composable
fun DetailScreen(
    state: AddTaskContract.State,
    effectFlow: Flow<AddTaskContract.Effect>?,
    onEventSent: (event: AddTaskContract.Event) -> Unit,
    navigateBackToHome: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is AddTaskContract.Effect.Navigation.BackToHome -> navigateBackToHome()
                is AddTaskContract.Effect.ShowToast -> {
                    toastMessage(context = context, message = effect.message)
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {

        AddNewTaskScreen(
            isEditMode = state.taskTitle.isNotEmpty(),
            title = state.taskTitle,
            description = state.taskDescription,
            date = state.date,
            time = state.time,
            onAddTaskClicked = {
                onEventSent(
                    AddTaskContract.Event.OnAddTaskClicked(
                        task = TaskModel(
                            title = state.taskTitle,
                            description = state.taskDescription,
                            date = state.date,
                            time = state.time
                        )
                    )
                )
            },
            onTitleChanged = { onEventSent(AddTaskContract.Event.OnTaskTitleChanged(it)) },
            onDescriptionChanged = { onEventSent(AddTaskContract.Event.OnTaskDescriptionChanged(it)) },
            showPriorityPicker = {},
            showDatePicker = {
                onEventSent(AddTaskContract.Event.ShowDatePicker)
            },
            showTimePicker = {
                onEventSent(AddTaskContract.Event.ShowTimePicker)
            },
            onUpdateTaskClicked = {
                Log.d("UpdateTask", "DetailScreen: ${state.taskTitle}")
                onEventSent(
                    AddTaskContract.Event.OnUpdateTask(
                        task = TaskModel(
                            title = state.taskTitle,
                            description = state.taskDescription,
                            date = state.date,
                            time = state.time
                        )
                    )
                )
            }
        )

        if (state.showDatePicker) {
            MyDatePickerDialog(
                onDateSelected = {
                    onEventSent(AddTaskContract.Event.OnDateSelected(it))
                },
                onDismiss = { }
            )
        }

        if (state.showTimePicker) {
            TimePickerDialog(
                onCancel = { /*TODO*/ },
                onConfirm = {
                    val formattedTime = "${it.get(Calendar.HOUR_OF_DAY)}:${it.get(Calendar.MINUTE)}"
                    onEventSent(AddTaskContract.Event.OnTimeSelected(formattedTime))
                }
            )
        }

    }
}
