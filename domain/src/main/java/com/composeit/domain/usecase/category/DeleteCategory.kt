package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category

interface DeleteCategory {

    suspend operator fun invoke(category: Category)
}