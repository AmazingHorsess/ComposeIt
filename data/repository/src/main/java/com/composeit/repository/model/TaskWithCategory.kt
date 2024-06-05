package com.composeit.repository.model

data class TaskWithCategory(
    val task: Task,
    val category: Category? = null,
)