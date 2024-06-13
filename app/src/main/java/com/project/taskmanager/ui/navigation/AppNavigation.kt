package com.project.taskmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.taskmanager.ui.feature.splashScreen.SplashScreen
import com.project.taskmanager.ui.navigation.Navigation.Args.TASK_ID

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.Routes.SPLASH) {
        composable(Navigation.Routes.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(Navigation.Routes.TASK) {
            HomeScreenDestination(navController = navController)
        }
        composable(
            route = Navigation.Routes.DETAIL,
            arguments = listOf(navArgument(name = TASK_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val userId =
                requireNotNull(backStackEntry.arguments?.getString(TASK_ID)) { "User id is required as an argument" }
            TaskDetailScreenDestination(taskID = userId, navController = navController)

        }
    }
}

object Navigation {
    object Args {
        const val TASK_ID = "task_id"
    }

    object Routes {
        const val TASK = "tasks"
        const val SPLASH = "splash"
        const val DETAIL = "$TASK/{$TASK_ID}"
    }
}

fun NavController.navigateToRepos(userId: String) {
    navigate(route = "${Navigation.Routes.TASK}/$userId")
}

fun NavController.navigateToDetailScreen() {
    navigate(route = "${Navigation.Routes.TASK}/-1")
}

fun NavController.navigateToHomeScreen() {
    navigate(route = Navigation.Routes.TASK)
}
