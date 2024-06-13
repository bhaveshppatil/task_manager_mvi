package com.project.taskmanager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.project.taskmanager.data.model.TaskModel

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskToDB(task: TaskModel)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskModel>

    @Update
    suspend fun updateTask(task: TaskModel)

    @Query("SELECT * FROM tasks WHERE isCompleted = 0")
    suspend fun getInProgressTasks(): List<TaskModel>

    @Query("SELECT * FROM tasks WHERE isCompleted = 1")
    suspend fun getCompletedTasks(): List<TaskModel>

    @Query("UPDATE tasks SET isCompleted = 1 WHERE id = :taskId")
    suspend fun markTaskAsCompleted(taskId: Int)

    @Query("Delete FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun searchTaskInDatabase(searchQuery: String): List<TaskModel>

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): TaskModel


}