package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository

class UncompleteTask(
    private val taskRepository: TaskRepository

) {
    suspend operator fun invoke(task: Task) {
        val updatedTask = updateTaskAsUncompleted(task)
        return taskRepository.updateTask(updatedTask)
    }

    private fun updateTaskAsUncompleted(task: Task) =
        task.copy(completed = false, completedDate = null)
}