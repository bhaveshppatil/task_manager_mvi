package com.project.taskmanager.ui.feature.splashScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.taskmanager.R
import com.project.taskmanager.common.AnimatedPreloader
import com.project.taskmanager.common.VerticalSpacer
import com.project.taskmanager.ui.navigation.Navigation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
            text = "Task Manager",
            style = MaterialTheme.typography.headlineMedium
        )
        VerticalSpacer(size = 20.dp)
        Text(
            text = "The greatest glory in living lies not in never falling, but in rising every time we fall.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Navigation.Routes.TASK)
    }
}