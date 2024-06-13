package com.project.taskmanager.ui.feature.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.taskmanager.common.VerticalSpacer
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun TaskListView(
    tasks: List<TaskModel>,
    onTaskClick: (TaskModel) -> Unit,
    onEditClick: (TaskModel) -> Unit,
    onDeleteClick: (TaskModel) -> Unit,
    onCompletedClick: (TaskModel) -> Unit,
    onNewTaskClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {
        TaskHeader(
            onNewTaskClick = { onNewTaskClick.invoke() }
        )

        VerticalSpacer(size = 30.dp)

        LazyColumn {
            items(tasks) { task ->
                TaskItemView(
                    task = task,
                    onTaskClick = onTaskClick,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick,
                    onCompletedClick = onCompletedClick
                )
                VerticalSpacer(size = 10.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListViewPreview() {
    TaskManagerTheme {
        TaskListView(
            tasks = emptyList(),
            onTaskClick = {},
            onEditClick = {},
            onDeleteClick = {},
            onCompletedClick = {},
            onNewTaskClick = {}
        )
    }
}