package com.project.taskmanager.ui.feature.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.taskmanager.R
import com.project.taskmanager.common.AnimatedPreloader
import com.project.taskmanager.common.NewTaskButton
import com.project.taskmanager.common.VerticalSpacer
import com.project.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun EmptyTaskView(
    onNewTaskClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedPreloader(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(300.dp),
            rawResId = R.raw.empty,
            isPlaying = true
        )

        VerticalSpacer(size = 50.dp)

        Text(
            text = "Look's like you have not added any task yet.\nAdd your first task",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        VerticalSpacer(size = 20.dp)

        NewTaskButton(onClick = onNewTaskClick)

    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyTaskPreview() {
    TaskManagerTheme {
        EmptyTaskView()
    }
}