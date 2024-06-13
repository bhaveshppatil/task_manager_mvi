package com.project.taskmanager.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomIcon(
    @DrawableRes id: Int,
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = "Task Icons",
        tint = Color.Black,
        modifier = Modifier
            .size(30.dp)
            .clickable {
                onClick.invoke()
            }
    )
}
