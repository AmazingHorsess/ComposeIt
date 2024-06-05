package com.composeit.category.di

import com.amazinghorsess.category_api.presentation.CategoryListViewModel
import com.composeit.category.mapper.CategoryMapper
import com.composeit.category.presentation.bottomsheet.CategoryAddViewModel
import com.composeit.category.presentation.bottomsheet.CategoryEditViewModel
import com.composeit.category.presentation.list.CategoryListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Category dependency injection module.
 */
val categoryModule = module {
    viewModelOf(::CategoryListViewModelImpl) bind CategoryListViewModel::class
    viewModelOf(::CategoryAddViewModel)
    viewModelOf(::CategoryEditViewModel)

    // Mapper
    factoryOf(::CategoryMapper)
}