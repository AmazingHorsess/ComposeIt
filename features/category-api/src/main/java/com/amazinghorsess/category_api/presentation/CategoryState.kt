package com.amazinghorsess.category_api.presentation

import com.amazinghorsess.category_api.model.Category

sealed class CategoryState {



    object Loading: CategoryState()

    data class Loaded(val categoryList: List<Category>) : CategoryState()

    object Empty: CategoryState()
}