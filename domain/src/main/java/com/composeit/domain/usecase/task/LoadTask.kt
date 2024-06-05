package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task

interface LoadTask {
    suspend operator fun invoke(taskId:Long): Task?
}