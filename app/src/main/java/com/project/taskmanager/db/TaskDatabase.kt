package com.project.taskmanager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.taskmanager.data.model.TaskModel

@Database(entities = [(TaskModel::class)], version = 2)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun getTaskDAO(): TaskDAO
}