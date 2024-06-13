package com.project.taskmanager.data

import android.util.Log
import com.project.taskmanager.data.model.TaskModel
import com.project.taskmanager.db.TaskDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDAO: TaskDAO) {

    private suspend fun <T> safeCall(call: suspend () -> T): Result<T> {
        return try {
            Result.success(call())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun insertTask(task: TaskModel) = withContext(Dispatchers.IO) {
        safeCall { taskDAO.insertTaskToDB(task) }
    }

    suspend fun getAllTasks() = withContext(Dispatchers.IO) {
        safeCall { taskDAO.getAllTasks() }
    }

    suspend fun updateTask(task: TaskModel) = withContext(Dispatchers.IO) {
        safeCall { taskDAO.updateTask(task) }
    }

    suspend fun getInProgressTasks() = withContext(Dispatchers.IO) {
        safeCall { taskDAO.getInProgressTasks() }
    }

    suspend fun getCompletedTasks() = withContext(Dispatchers.IO) {
        safeCall { taskDAO.getCompletedTasks() }
    }

    suspend fun markTaskAsCompleted(taskId: Int) = withContext(Dispatchers.IO) {
        safeCall { taskDAO.markTaskAsCompleted(taskId) }
    }

    suspend fun deleteTaskById(taskId: Int) = withContext(Dispatchers.IO) {
        safeCall { taskDAO.deleteTaskById(taskId) }
    }

    suspend fun searchTaskInDatabase(searchQuery: String) = withContext(Dispatchers.IO) {
        safeCall { taskDAO.searchTaskInDatabase(searchQuery) }
    }

    suspend fun deleteAllTasks() = withContext(Dispatchers.IO) {
        safeCall { taskDAO.deleteAllTasks() }
    }

    suspend fun getTaskByID(taskId: Int) = withContext(Dispatchers.IO) {
        safeCall { taskDAO.getTaskById(taskId) }
    }
}
