package com.composeit.task.model

import com.amazinghorsess.category_api.model.Category

data class TaskWithCategory(
    val task: Task,
    val category: Category? = null,
)