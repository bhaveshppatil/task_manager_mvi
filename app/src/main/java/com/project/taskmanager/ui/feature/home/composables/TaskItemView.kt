package com.project.taskmanager.ui.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.taskmanager.common.VerticalSpacer
import com.project.taskmanager.common.orDefault
import com.project.taskmanager.common.utils.TaskStatusDropDownMenu
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.ui.theme.LightGreen
import com.project.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun TaskItemView(
    task: TaskModel,
    onTaskClick: (TaskModel) -> Unit,
    onEditClick: (TaskModel) -> Unit,
    onDeleteClick: (TaskModel) -> Unit,
    onCompletedClick: (TaskModel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = LightGreen
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    onTaskClick.invoke(task)
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = task.title.orDefault(),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium, fontSize = 20.sp
                        ),
                    )
                    Text(
                        text = task.description.orDefault(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                TaskStatusDropDownMenu(
                    menuState = false,
                    onEditClicked = {
                        onEditClick(task)
                        expanded = false
                    },
                    onDeleteClicked = {
                        onDeleteClick(task)
                        expanded = false
                    },
                    onCompletedClicked = {
                        onCompletedClick(task)
                        expanded = false
                    }
                )
            }

            VerticalSpacer(size = 15.dp)

            HorizontalDivider()

            VerticalSpacer(size = 15.dp)


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Text(
                    text = "Date: ${task.date.orDefault()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "Time: ${task.time.orDefault()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

            }

        }
    }
}

@Preview
@Composable
private fun TaskItemViewPreview() {
    TaskManagerTheme {
        TaskItemView(
            task = TaskModel(
                id = 1,
                title = "Title 1",
                description = "Description 1",
                date = "2023-09-01",
                time = "10:00",
                isCompleted = false,
                priority = "High"
            ),
            onTaskClick = {},
            onDeleteClick = {},
            onEditClick = {},
            onCompletedClick = {}
        )
    }
}