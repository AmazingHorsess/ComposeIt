package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface LoadAllCategories {

    operator fun invoke(): Flow<List<Category>>
}