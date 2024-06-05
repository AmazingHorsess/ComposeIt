package com.composeitdatastore.di

import com.composeit.repository.datasource.PreferencesDataSource
import com.composeitdatastore.datasource.PreferencesDataSourceImpl
import com.composeitdatastore.mapper.AppThemeOptionsMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataStoreModule = module {

    // Data Source
    singleOf(::PreferencesDataSourceImpl) bind PreferencesDataSource::class

    // Mappers
    factoryOf(::AppThemeOptionsMapper)
}