package com.project.taskmanager.ui.feature.home.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.taskmanager.common.HorizontalSpacer
import com.project.taskmanager.common.NewTaskButton
import com.project.taskmanager.common.VerticalSpacer
import com.project.taskmanager.common.utils.currentDateFormatted
import com.project.taskmanager.ui.theme.LightGreen
import com.project.taskmanager.ui.theme.Purple80
import com.project.taskmanager.ui.theme.PurpleGrey80
import com.project.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun TaskHeader(
    onNewTaskClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors().copy(
            containerColor = LightGreen
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Today's Tasks",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Default
                    )
                )

                VerticalSpacer(size = 5.dp)

                Text(
                    text = currentDateFormatted(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.SansSerif,
                    ),
                    color = Color.Gray
                )
            }
            NewTaskButton(onClick = onNewTaskClick)
        }
    }
}

@Preview
@Composable
private fun TaskHeaderPreview() {
    TaskManagerTheme {
        TaskHeader(onNewTaskClick = {})
    }
}