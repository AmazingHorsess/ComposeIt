package com.composeit.repository

import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository
import com.composeit.repository.datasource.TaskDataSource
import com.composeit.repository.mapper.TaskMapper

internal class TaskRepositoryImpl(
    private val dataSource: TaskDataSource,
    private val mapper: TaskMapper
): TaskRepository {
    override suspend fun insertTask(task: Task) =
        dataSource.insertTask(mapper.toRepo(task))

    override suspend fun updateTask(task: Task) =
        dataSource.updateTask(mapper.toRepo(task))

    override suspend fun deleteTask(task: Task) =
        dataSource.deleteTask(mapper.toRepo(task))

    override suspend fun cleanTable() =
        dataSource.cleanTable()

    override suspend fun findTaskById(taskId: Long): Task? =
        dataSource.findTaskById(taskId)?.let { mapper.toDomain(it) }

    override suspend fun findAllTasksWithDueDate(): List<Task>  =
        dataSource.findAllTasksWithDueDate().map { mapper.toDomain(it) }

}