package com.project.taskmanager.common

fun String?.orDefault(): String {
    return this ?: "-"
}

