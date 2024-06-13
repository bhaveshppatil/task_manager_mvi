package com.project.taskmanager.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp


@Composable
fun VerticalSpacer(size: Dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun HorizontalSpacer(size: Dp) {
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun TaskImage(id: Int, modifier: Modifier = Modifier, contentDescription: String? = null) {
    Image(
        painter = painterResource(id = id),
        contentScale = ContentScale.Fit,
        contentDescription = contentDescription,
        modifier = modifier
    )
}