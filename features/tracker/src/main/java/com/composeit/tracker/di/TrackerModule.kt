package com.composeit.tracker.di

import com.composeit.tracker.mapper.TrackerMapper
import com.composeit.tracker.presentation.TrackerViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val trackerModule = module {
    viewModelOf(::TrackerViewModel)
    factoryOf(::TrackerMapper)
}
