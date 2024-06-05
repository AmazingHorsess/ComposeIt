package com.composeit.repository

import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.TaskWithCategoryRepository
import com.composeit.repository.datasource.TaskWithCategoryDataSource
import com.composeit.repository.mapper.TaskWithCategoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TaskWithCategoryRepositoryImpl(
    private val dataSource: TaskWithCategoryDataSource,
    private val mapper: TaskWithCategoryMapper
): TaskWithCategoryRepository {
    override fun findAllTasksWithCategory(): Flow<List<TaskWithCategory>> =
        dataSource.findAllTasksWithCategory().map { mapper.toDomain(it) }

    override fun findAllTasksWithCategoryId(categoryId: Long): Flow<List<TaskWithCategory>> =
        dataSource.findAllTasksWithCategoryId(categoryId).map { mapper.toDomain(it) }
}