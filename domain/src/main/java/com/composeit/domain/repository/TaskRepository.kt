package com.composeit.domain.repository

import com.composeit.domain.model.Task

interface TaskRepository {

    suspend fun insertTask(task:Task)

    suspend fun updateTask(task:Task)

    suspend fun deleteTask(task: Task)

    suspend fun cleanTable()

    suspend fun findTaskById(taskId:Long): Task?

    suspend fun findAllTasksWithDueDate(): List<Task>
}