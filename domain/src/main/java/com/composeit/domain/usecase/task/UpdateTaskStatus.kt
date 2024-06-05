package com.composeit.domain.usecase.task

interface UpdateTaskStatus {

    suspend operator fun invoke(taskId: Long)
}