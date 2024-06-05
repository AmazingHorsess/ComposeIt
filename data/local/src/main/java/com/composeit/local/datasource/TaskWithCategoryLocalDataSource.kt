package com.composeit.local.datasource

import com.composeit.local.mapper.TaskWithCategoryMapper
import com.composeit.local.provider.DaoProvider
import com.composeit.repository.datasource.TaskWithCategoryDataSource
import com.composeit.repository.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TaskWithCategoryLocalDataSource(
    daoProvider: DaoProvider,
    private val mapper: TaskWithCategoryMapper,
) : TaskWithCategoryDataSource {

    private val taskWithCategoryDao = daoProvider.getTaskWithCategoryDao()

    override fun findAllTasksWithCategory(): Flow<List<TaskWithCategory>> =
        taskWithCategoryDao.findAllTasksWithCategory().map { mapper.toRepo(it) }

    override fun findAllTasksWithCategoryId(categoryId: Long): Flow<List<TaskWithCategory>> =
        taskWithCategoryDao.findAllTaskWithCategoryId(categoryId).map { mapper.toRepo(it) }
}