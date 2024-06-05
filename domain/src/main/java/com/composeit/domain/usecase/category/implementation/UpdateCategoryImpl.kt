package com.composeit.domain.usecase.category.implementation

import com.composeit.domain.model.Category
import com.composeit.domain.repository.CategoryRepository
import com.composeit.domain.usecase.category.UpdateCategory

internal class UpdateCategoryImpl(
    private val categoryRepository: CategoryRepository
): UpdateCategory {

    override suspend fun invoke(category: Category) =
        categoryRepository.updateCategory(category)





}