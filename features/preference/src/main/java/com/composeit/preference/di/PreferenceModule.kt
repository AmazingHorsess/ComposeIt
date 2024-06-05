package com.composeit.preference.di

import com.composeit.preference.mapper.AppThemeOptionsMapper
import com.composeit.preference.presentation.PreferenceViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val preferenceModule = module {

    factoryOf(::AppThemeOptionsMapper)

    viewModelOf(::PreferenceViewModel)

    
}