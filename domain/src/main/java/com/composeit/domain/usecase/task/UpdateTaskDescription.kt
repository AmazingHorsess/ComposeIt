package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task

interface UpdateTaskDescription {

    suspend operator fun invoke(taskId: Long, description: String)
}