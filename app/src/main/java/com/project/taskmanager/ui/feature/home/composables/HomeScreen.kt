package com.project.taskmanager.ui.feature.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.project.taskmanager.common.toastMessage
import com.project.taskmanager.common.utils.Progress
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.base.SIDE_EFFECTS_KEY
import com.project.taskmanager.ui.feature.home.HomeScreenContract.State
import com.project.taskmanager.ui.feature.home.HomeScreenContract.Effect
import com.project.taskmanager.ui.feature.home.HomeScreenContract.Event
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    state: State,
    effectFlow: Flow<Effect>?,
    onEventSent: (event: Event) -> Unit,
    navigateToTaskDetail: (taskID: String) -> Unit,
    navigateToNewTask: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is Effect.Navigation.NavigateToTaskDetail -> navigateToTaskDetail(effect.taskID)
                Effect.Navigation.NavigateToNewTask -> navigateToNewTask.invoke()
                is Effect.ShowToast -> {
                    toastMessage(context, effect.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        when {
            state.isLoading -> {
                Progress()
            }

            state.isTaskListEmpty -> {
                EmptyTaskView(onNewTaskClick = { onEventSent(Event.OnNewTaskClick) })
            }

            else ->
                TaskListView(
                    tasks = state.tasks,
                    onTaskClick = { onEventSent(Event.OnTaskClick("${it.id}")) },
                    onEditClick = { onEventSent(Event.OnEditClick("${it.id}")) },
                    onDeleteClick = { onEventSent(Event.OnDeleteClick(it)) },
                    onCompletedClick = { onEventSent(Event.OnCompletedClick(it)) },
                    onNewTaskClick = { onEventSent(Event.OnNewTaskClick) }
                )
        }
    }
}