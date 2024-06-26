package com.composeit.task.presentation.list

import com.composeit.task.model.TaskWithCategory

internal data class TaskStateHandler(
    val state: TaskListViewState = TaskListViewState.Empty,
    val onCheckedChange: (TaskWithCategory) -> Unit = {},
    val onItemClick: (Long) -> Unit,
    val onAddClick: () -> Unit,
)
