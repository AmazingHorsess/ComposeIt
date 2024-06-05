package com.composeit.local.model

import androidx.room.Embedded

data class TaskWithCategory(
    @Embedded val task: Task,
    @Embedded val category: Category? = null,
)
