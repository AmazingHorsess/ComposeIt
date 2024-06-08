package com.composeit.glance.data

import com.composeit.domain.usecase.task.UpdateTaskStatus
import com.composeit.domain.usecase.taskwithcategory.LoadUncompletedTasks
import com.composeit.glance.mapper.TaskMapper
import com.composeit.glance.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TaskListGlanceUpdater(
    private val loadAllTasksUseCase: LoadUncompletedTasks,
    private val updateTaskStatus: UpdateTaskStatus,
    private val taskMapper: TaskMapper,
) {

    fun loadTaskList(categoryId: Long? = null): Flow<List<Task>> =
        loadAllTasksUseCase(categoryId = categoryId).map { taskMapper.toView(it) }

    suspend fun updateTaskAsCompleted(taskId: Long) =
        updateTaskStatus(taskId)
}