package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category

interface UpdateCategory {

    suspend operator fun invoke(category: Category)
}