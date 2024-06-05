package com.composeit.domain.usecase.tracker

import com.composeit.domain.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow

interface LoadCompletedTasksByPeriod {
    operator fun invoke(): Flow<List<TaskWithCategory>>
}