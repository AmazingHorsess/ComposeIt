package com.composeit.search.di

import com.composeit.search.mapper.TaskSearchMapper
import com.composeit.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val searchModule = module {

    viewModelOf(::SearchViewModel)

    factoryOf(::TaskSearchMapper)
}