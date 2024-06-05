package com.composeit.local.di

import com.composeit.local.datasource.CategoryLocalDataSource
import com.composeit.local.datasource.SearchLocalDataSource
import com.composeit.local.datasource.TaskWithCategoryLocalDataSource
import com.composeit.local.datasource.TaskLocalDataSource
import com.composeit.local.mapper.AlarmIntervalMapper
import com.composeit.local.mapper.CategoryMapper
import com.composeit.local.mapper.TaskMapper
import com.composeit.local.mapper.TaskWithCategoryMapper
import com.composeit.local.provider.DaoProvider
import com.composeit.local.provider.DatabaseProvider
import com.composeit.repository.datasource.CategoryDataSource
import com.composeit.repository.datasource.SearchDataSource
import com.composeit.repository.datasource.TaskDataSource
import com.composeit.repository.datasource.TaskWithCategoryDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Local dependency injection module.
 */
val localModule = module {

    // Data Sources
    singleOf(::TaskLocalDataSource) bind TaskDataSource::class
    singleOf(::CategoryLocalDataSource) bind CategoryDataSource::class
    singleOf(::TaskWithCategoryLocalDataSource) bind TaskWithCategoryDataSource::class
    singleOf(::SearchLocalDataSource) bind SearchDataSource::class

    // Mappers
    factoryOf(::AlarmIntervalMapper)
    factoryOf(::TaskMapper)
    factoryOf(::CategoryMapper)
    factoryOf(::TaskWithCategoryMapper)

    // Providers
    singleOf(::DatabaseProvider)
    singleOf(::DaoProvider)
}
