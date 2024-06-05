package com.composeit.task.presentation.detail.main

import androidx.lifecycle.ViewModel
import com.composeit.domain.usecase.task.LoadTask
import com.composeit.domain.usecase.task.UpdateTaskCategory
import com.composeit.domain.usecase.task.UpdateTaskDescription
import com.composeit.domain.usecase.task.UpdateTaskTitle
import com.composeit.task.mapper.TaskMapper
import com.libraries.core.coroutines.AppCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TaskDetailViewModel(
    private val loadTaskUseCase: LoadTask,
    private val updateTaskTitle: UpdateTaskTitle,
    private val updateTaskDescription: UpdateTaskDescription,
    private val updateTaskCategory: UpdateTaskCategory,
    private val applicationScope: AppCoroutineScope,
    private val taskMapper: TaskMapper,
) : ViewModel() {

    fun loadTaskInfo(taskId: TaskId): Flow<TaskDetailState> = flow {
        val task = loadTaskUseCase(taskId = taskId.value)

        if (task != null) {
            val viewTask = taskMapper.toView(task)
            emit(TaskDetailState.Loaded(viewTask))
        } else {
            emit(TaskDetailState.Error)
        }
    }

    fun updateTitle(taskId: TaskId, title: String) =
        applicationScope.launch {
            updateTaskTitle(taskId.value, title)
        }

    fun updateDescription(taskId: TaskId, description: String) =
        applicationScope.launch {
            updateTaskDescription(taskId.value, description)
        }

    fun updateCategory(taskId: TaskId, categoryId: CategoryId) =
        applicationScope.launch {
            updateTaskCategory(taskId = taskId.value, categoryId = categoryId.value)
        }
}