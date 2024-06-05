package com.composeit.glance.di

import com.composeit.domain.interactor.GlanceInteractor
import com.composeit.glance.data.TaskListGlanceUpdater
import com.composeit.glance.interactor.GlanceInteractorImpl
import com.composeit.glance.mapper.TaskMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val glanceModule = module {
    singleOf(::TaskListGlanceUpdater)
    factoryOf(::GlanceInteractorImpl) bind GlanceInteractor::class
    factoryOf(::TaskMapper)

}