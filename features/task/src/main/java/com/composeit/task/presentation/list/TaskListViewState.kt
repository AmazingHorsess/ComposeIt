package com.composeit.task.presentation.list

import com.composeit.task.model.TaskWithCategory

internal sealed class TaskListViewState {

    object Loading : TaskListViewState()

    data class Error(val cause: Throwable) : TaskListViewState()

    data class Loaded(val items: List<TaskWithCategory>) : TaskListViewState()

    object Empty : TaskListViewState()
}