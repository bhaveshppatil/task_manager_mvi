package com.project.taskmanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo val title: String? = null,
    @ColumnInfo val description: String? = null,
    @ColumnInfo val date: String? = null,
    @ColumnInfo val time: String? = null,
    @ColumnInfo val isCompleted: Boolean? = false,
    @ColumnInfo val color: String? = null,
    @ColumnInfo val priority: String? = "High",
    @ColumnInfo val category: String? = "Daily"
)

fun TaskModel?.isNotNull(): Boolean {
    return this?.title?.isNotEmpty() ?: false &&
            this?.description?.isNotEmpty() ?: false &&
            this?.date?.isNotEmpty() ?: false &&
            this?.time?.isNotEmpty() ?: false
}
