package com.composeit.task.presentation.list

import com.amazinghorsess.category_api.presentation.CategoryState
import com.composeit.task.presentation.detail.main.CategoryId

internal data class CategoryStateHandler (
    val state: CategoryState = CategoryState.Empty,
    val currentCategory: CategoryId? = null,
    val onCategoryChange: (CategoryId) -> Unit = {}
)