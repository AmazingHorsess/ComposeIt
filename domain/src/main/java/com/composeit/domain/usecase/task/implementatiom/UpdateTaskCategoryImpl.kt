package com.composeit.domain.usecase.task.implementatiom

import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.task.LoadTask
import com.composeit.domain.usecase.task.UpdateTask
import com.composeit.domain.usecase.task.UpdateTaskCategory

internal class UpdateTaskCategoryImpl(
    private val loadTask: LoadTask,
    private val updateTask: UpdateTask
): UpdateTaskCategory {
    override suspend fun invoke(taskId: Long, categoryId: Long?) {
        val task = loadTask(taskId) ?: return
        val updatedTask = task.copy(categoryId=categoryId)
        updateTask(updatedTask)
    }
}