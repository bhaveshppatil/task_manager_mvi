package com.project.taskmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.project.taskmanager.ui.feature.home.TaskHomeViewModel
import com.project.taskmanager.ui.feature.home.composables.HomeScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreenDestination(navController: NavController) {
    val viewModel = getViewModel<TaskHomeViewModel>()

    HomeScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event ->  viewModel.setEvent(event) },
        navigateToTaskDetail = { task ->
            navController.navigateToRepos(task)
        },
        navigateToNewTask = {
            navController.navigateToDetailScreen()
        }
    )
}
