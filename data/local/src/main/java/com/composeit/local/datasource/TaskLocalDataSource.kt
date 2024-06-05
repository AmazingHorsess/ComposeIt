package com.composeit.local.datasource

import com.composeit.local.mapper.TaskMapper
import com.composeit.local.provider.DaoProvider
import com.composeit.repository.datasource.TaskDataSource
import com.composeit.repository.model.Task

internal class TaskLocalDataSource(daoProvider: DaoProvider, private val taskMapper: TaskMapper) :
    TaskDataSource {

    private val taskDao = daoProvider.getTaskDao()

    override suspend fun insertTask(task: Task) =
        taskDao.insertTask(taskMapper.toLocal(task))

    override suspend fun updateTask(task: Task) =
        taskDao.updateTask(taskMapper.toLocal(task))

    override suspend fun deleteTask(task: Task) =
        taskDao.deleteTask(taskMapper.toLocal(task))

    override suspend fun cleanTable() =
        taskDao.cleanTable()

    override suspend fun findAllTasksWithDueDate(): List<Task> =
        taskDao.findAllTasksWithDueDate().map { taskMapper.toRepo(it) }

    override suspend fun findTaskById(taskId: Long): Task? =
        taskDao.getTaskById(taskId)?.let { taskMapper.toRepo(it) }
}