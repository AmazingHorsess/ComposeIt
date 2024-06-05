package com.composeit.domain.usecase.category.implementation

import com.composeit.domain.model.Category
import com.composeit.domain.repository.CategoryRepository
import com.composeit.domain.usecase.category.DeleteCategory

internal class DeleteCategoryImpl(
    private val categoryRepository: CategoryRepository
): DeleteCategory {
    override suspend fun invoke(category: Category) =
        categoryRepository.deleteCategory(category)


}