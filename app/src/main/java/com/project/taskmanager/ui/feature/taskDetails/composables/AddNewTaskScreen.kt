package com.project.taskmanager.ui.feature.taskDetails.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.taskmanager.R
import com.project.taskmanager.common.CustomIcon
import com.project.taskmanager.common.NewTaskButton
import com.project.taskmanager.common.VerticalSpacer
import com.project.taskmanager.ui.theme.LightGreen
import com.project.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun AddNewTaskScreen(
    title: String,
    date: String,
    time: String,
    description: String,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onAddTaskClicked: () -> Unit,
    showDatePicker: () -> Unit,
    showTimePicker: () -> Unit,
    showPriorityPicker: () -> Unit,
    onUpdateTaskClicked: () -> Unit,
    isEditMode: Boolean
) {
    Log.d("AddNewTaskScreen", "isEditMode: $isEditMode")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Add New Task",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGreen, RoundedCornerShape(10.dp))
                    .requiredHeight(100.dp)
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            val taskTitle = title ?: ""
            val taskDescription = description ?: ""

            TextField(
                value = taskTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onValueChange = { onTitleChanged(it) },
                colors = TextFieldDefaults.colors()
                    .copy(unfocusedContainerColor = Color.Transparent),
                placeholder = { Text(text = "Task title") }
            )

            TextField(
                value = taskDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onValueChange = { onDescriptionChanged(it) },
                colors = TextFieldDefaults.colors()
                    .copy(unfocusedContainerColor = Color.Transparent),
                placeholder = { Text(text = "Task description") }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val date = date ?: ""

                Text(
                    text = "Task Date: ",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val time = time ?: ""

                Text(
                    text = "Task Time: ",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val priority = "High"

                Text(
                    text = "Task Priority: ",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = priority,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CustomIcon(
                        id = R.drawable.baseline_calendar_month_24,
                        onClick = showDatePicker
                    )
                    CustomIcon(
                        id = R.drawable.baseline_access_time_filled_24,
                        onClick = showTimePicker
                    )
                    CustomIcon(
                        id = R.drawable.baseline_flag_24,
                        onClick = showPriorityPicker
                    )
                }

                NewTaskButton(
                    title = if (isEditMode) "Update" else "Add Task",
                    onClick = {
                        if (isEditMode)
                            onUpdateTaskClicked.invoke()
                        else onAddTaskClicked.invoke()
                    }
                )

            }

            VerticalSpacer(size = 30.dp)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AddNewTaskPreview() {
    TaskManagerTheme {
        AddNewTaskScreen(
            title = "Title",
            date = "Date",
            time = "Time",
            description = "Description",
            onTitleChanged = {},
            onDescriptionChanged = {},
            onAddTaskClicked = {},
            showDatePicker = {},
            showTimePicker = {},
            showPriorityPicker = {},
            isEditMode = false,
            onUpdateTaskClicked = {}
        )
    }
}