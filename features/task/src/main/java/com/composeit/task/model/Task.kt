package com.composeit.task.model

import java.util.Calendar

data class Task(
    val id: Long = 0,
    val completed: Boolean = false,
    val title: String,
    val description: String? = null,
    val categoryId: Long? = null,
    val dueDate: Calendar? = null,
    val creationDate: Calendar? = null,
    val completedDate: Calendar? = null,
    val isRepeating: Boolean = false,
    val alarmInterval: AlarmInterval? = null,

    )


