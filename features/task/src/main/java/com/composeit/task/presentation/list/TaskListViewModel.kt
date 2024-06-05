package com.composeit.task.presentation.list

import androidx.lifecycle.ViewModel
import com.composeit.domain.usecase.task.UpdateTaskStatus
import com.composeit.domain.usecase.taskwithcategory.LoadUncompletedTasks
import com.composeit.task.mapper.TaskWithCategoryMapper
import com.composeit.task.model.TaskWithCategory
import com.composeit.task.presentation.detail.main.CategoryId
import com.libraries.core.coroutines.AppCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class TaskListViewModel(
    private val loadAllTasksUseCase: LoadUncompletedTasks,
    private val updateTaskStatus: UpdateTaskStatus,
    private val applicationScope: AppCoroutineScope,
    private val taskWithCategoryMapper: TaskWithCategoryMapper,
    ): ViewModel() {

        fun loadAllTask(categoryId: Long? = null): Flow<TaskListViewState> = flow {
            loadAllTasksUseCase(categoryId = categoryId)
                .map { task ->
                    taskWithCategoryMapper.toView(task)
                }
                .catch { error ->
                    emit(TaskListViewState.Error(error))
                }
                .collect{ tasks ->
                    val state = if (tasks.isNotEmpty()) {
                        TaskListViewState.Loaded(tasks)
                        
                    } else{
                        TaskListViewState.Empty
                    }
                    emit(state)
                }
        }

    fun updateTaskStatus(item: TaskWithCategory) = applicationScope.launch {
        updateTaskStatus(item.task.id)
    }
}