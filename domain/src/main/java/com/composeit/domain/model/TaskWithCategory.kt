package com.composeit.domain.model

data class TaskWithCategory(
    val task: Task,
    val category: Category? = null,
)
