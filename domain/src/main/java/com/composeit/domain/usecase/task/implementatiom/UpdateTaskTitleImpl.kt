package com.composeit.domain.usecase.task.implementatiom

import com.composeit.domain.interactor.GlanceInteractor
import com.composeit.domain.usecase.task.LoadTask
import com.composeit.domain.usecase.task.UpdateTask
import com.composeit.domain.usecase.task.UpdateTaskTitle

internal class UpdateTaskTitleImpl(
    private val loadTask: LoadTask,
    private val updateTask: UpdateTask,
    private val glanceInteractor: GlanceInteractor,
): UpdateTaskTitle {
    override suspend fun invoke(taskId: Long, title: String) {
        val task = loadTask(taskId) ?: return
        val updatedTask = task.copy(title = title)
        updateTask(updatedTask)
        glanceInteractor.onTaskListUpdated()
    }
}