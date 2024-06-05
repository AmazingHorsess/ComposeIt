package com.composeit.task.presentation.detail.main

import com.composeit.task.model.Task

internal sealed class TaskDetailState {

    object Loading : TaskDetailState()

    object Error : TaskDetailState()

    data class Loaded(val task: Task) : TaskDetailState()
}