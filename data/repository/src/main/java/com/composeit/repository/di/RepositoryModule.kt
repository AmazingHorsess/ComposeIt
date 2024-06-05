package com.composeit.repository.di

import com.composeit.domain.repository.CategoryRepository
import com.composeit.domain.repository.SearchRepository
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.repository.TaskWithCategoryRepository
import com.composeit.domain.usecase.preferences.PreferencesRepository
import com.composeit.repository.CategoryRepositoryImpl
import com.composeit.repository.PreferenceRepositoryImpl
import com.composeit.repository.SearchRepositoryImpl
import com.composeit.repository.TaskRepositoryImpl
import com.composeit.repository.TaskWithCategoryRepositoryImpl
import com.composeit.repository.datasource.TaskWithCategoryDataSource
import com.composeit.repository.mapper.AlarmIntervalMapper
import com.composeit.repository.mapper.AppThemeOptionsMapper
import com.composeit.repository.mapper.CategoryMapper
import com.composeit.repository.mapper.TaskMapper
import com.composeit.repository.mapper.TaskWithCategoryMapper
import com.composeit.repository.model.TaskWithCategory
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
/**
 * Repository dependency injection module.
 */
val repositoryModule = module {

    // Repositories
    singleOf(::TaskRepositoryImpl) bind TaskRepository::class
    singleOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    singleOf(::TaskWithCategoryRepositoryImpl) bind TaskWithCategoryRepository::class
    singleOf(::SearchRepositoryImpl) bind SearchRepository::class
    singleOf(::PreferenceRepositoryImpl) bind PreferencesRepository::class

    // Mappers
    factoryOf(::AlarmIntervalMapper)
    factoryOf(::TaskMapper)
    factoryOf(::CategoryMapper)
    factoryOf(::TaskWithCategoryMapper)
    factoryOf(::AppThemeOptionsMapper)
}