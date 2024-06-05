package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category

interface AddCategory {

    suspend operator fun invoke(category: Category)
}