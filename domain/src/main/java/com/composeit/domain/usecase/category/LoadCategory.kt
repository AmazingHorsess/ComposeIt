package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category

interface LoadCategory {
    suspend operator fun invoke(categoryId: Long): Category?
}