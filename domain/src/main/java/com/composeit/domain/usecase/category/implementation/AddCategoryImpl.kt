package com.composeit.domain.usecase.category.implementation

import com.composeit.domain.model.Category
import com.composeit.domain.repository.CategoryRepository
import com.composeit.domain.repository.TaskWithCategoryRepository
import com.composeit.domain.usecase.category.AddCategory

internal class AddCategoryImpl(
    private val categoryRepository: CategoryRepository
): AddCategory {
    override suspend fun invoke(category: Category) {
        if (category.name.isBlank()){
            return
        }
        categoryRepository.insertCategory(category)
    }
}