package com.composeit.domain.usecase.fake

import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository
import java.util.TreeMap

internal class TaskRepositoryFake: TaskRepository {
    private val taskMap: TreeMap<Long,Task> = TreeMap()

    override suspend fun insertTask(task: Task) {
            val id = if(task.id == 0L){
                taskMap.lastKey() +1
            } else task.id
        taskMap[id] = task
    }

    override suspend fun updateTask(task: Task) {
        taskMap[task.id] = task
    }

    override suspend fun deleteTask(task: Task) {
        taskMap.remove(task.id)
    }

    override suspend fun cleanTable() {
        taskMap.clear()
    }

    override suspend fun findTaskById(taskId: Long): Task? =
       taskMap[taskId]


    override suspend fun findAllTasksWithDueDate(): List<Task> =
        taskMap.filter {
            it.value.dueDate != null
        }.values.toMutableList()

    fun findAlLTasks(): List<Task> =
        taskMap.values.toList()

}