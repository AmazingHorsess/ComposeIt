package com.composeit.di
import com.composeit.presentation.MainViewModel
import com.composeit.presentation.mapper.AppThemeOptionsMapper
import com.libraries.core.coroutines.AppCoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule= module {
    viewModelOf(::MainViewModel)
    factoryOf(::AppThemeOptionsMapper)
    single { AppCoroutineScope() }

}