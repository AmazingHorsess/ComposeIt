package com.composeit.domain.usecase.task.implementatiom

import com.composeit.domain.interactor.GlanceInteractor
import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.task.UpdateTask

internal class UpdateTaskImpl(
    private val taskRepository: TaskRepository,
    private val glanceInteractor: GlanceInteractor
): UpdateTask {

    override suspend fun invoke(task: Task) {
        taskRepository.updateTask(task)
        glanceInteractor.onTaskListUpdated()

    }
}