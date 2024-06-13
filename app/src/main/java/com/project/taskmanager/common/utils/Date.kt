package com.project.taskmanager.common.utils

import androidx.compose.material3.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun currentDateFormatted(): String {
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun getCurrentTimeFormatted(): String {
    val currentTime = Calendar.getInstance().time
    val timeFormat = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
    return timeFormat.format(currentTime)
}
