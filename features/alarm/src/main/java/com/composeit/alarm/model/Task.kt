package com.composeit.alarm.model

import java.util.Calendar

data class Task(
    val id: Long,
    val title: String,
    val dueDate: Calendar?,
    val isCompleted: Boolean,
)
