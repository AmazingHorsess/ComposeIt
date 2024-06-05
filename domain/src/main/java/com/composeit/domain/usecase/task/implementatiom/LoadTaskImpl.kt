package com.composeit.domain.usecase.task.implementatiom

import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.task.LoadTask

internal class LoadTaskImpl(
    private val taskRepository: TaskRepository

):LoadTask {
    override suspend operator fun invoke(taskId: Long): Task? =
        taskRepository.findTaskById(taskId)

}