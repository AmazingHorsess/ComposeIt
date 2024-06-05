package com.composeit.domain.usecase.fake

import com.composeit.domain.model.Category
import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.TaskWithCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TaskWithCategoryRepositoryFake(
    private val taskRepository: TaskRepositoryFake,
    private val categoryRepository: CategoryRepositoryFake

): TaskWithCategoryRepository {

    override fun findAllTasksWithCategory(): Flow<List<TaskWithCategory>> =
        flow{
            val tasks = taskRepository.findAlLTasks()
                .map { task -> TaskWithCategory(task,findCategory(task.categoryId))  }
            emit(tasks)

        }

    override fun findAllTasksWithCategoryId(categoryId: Long): Flow<List<TaskWithCategory>> =
        flow {
            val tasks = taskRepository.findAlLTasks()
                .map { task -> TaskWithCategory(task, findCategory(task.categoryId)) }
                .filter { it.category?.id == categoryId }
            emit(tasks)
        }

    private suspend fun findCategory(categoryId: Long?): Category?{
        if (categoryId == null){
            return null
        }
        return categoryRepository.findCategoryById(categoryId)
    }
}