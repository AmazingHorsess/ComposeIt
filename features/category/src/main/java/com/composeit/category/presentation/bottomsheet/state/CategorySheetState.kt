package com.composeit.category.presentation.bottomsheet.state

import com.amazinghorsess.category_api.model.Category


internal sealed class CategorySheetState {

    data object Empty: CategorySheetState()

    data class Loaded(val category: Category): CategorySheetState()
}