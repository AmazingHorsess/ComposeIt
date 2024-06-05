package com.composeit.domain.usecase.task.implementatiom

import com.composeit.domain.usecase.task.LoadTask
import com.composeit.domain.usecase.task.UpdateTask
import com.composeit.domain.usecase.task.UpdateTaskDescription

internal class UpdateTaskDescriptionImpl(
    private val loadTask: LoadTask,
    private val updateTask: UpdateTask
): UpdateTaskDescription {
    override suspend fun invoke(taskId: Long, description: String) {
        val task = loadTask(taskId) ?:return
        val updatedTask = task.copy(description = description)
        updateTask(updatedTask)
    }
}