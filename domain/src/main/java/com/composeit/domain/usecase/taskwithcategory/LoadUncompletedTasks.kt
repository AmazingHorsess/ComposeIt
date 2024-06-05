package com.composeit.domain.usecase.taskwithcategory

import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.TaskWithCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LoadUncompletedTasks {

    operator fun invoke(categoryId: Long? = null): Flow<List<TaskWithCategory>>
}