package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task

interface UpdateTask {

    suspend operator fun invoke(task: Task)
}