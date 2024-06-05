package com.composeit.domain.usecase.fake

import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SearchRepositoryFake(
    taskRepositoryFake: TaskRepositoryFake,
    categoryRepositoryFake: CategoryRepositoryFake = CategoryRepositoryFake()
): SearchRepository {
    private val taskWithCategoryRepositoryFake = TaskWithCategoryRepositoryFake(taskRepositoryFake,categoryRepositoryFake)
    override suspend fun findTaskById(query: String): Flow<List<TaskWithCategory>> =
        taskWithCategoryRepositoryFake.findAllTasksWithCategory()
            .map { list: List<TaskWithCategory> ->
                list.filter { taskWithCategory ->
                    taskWithCategory.task.title.contains(query)
                }
            }


}