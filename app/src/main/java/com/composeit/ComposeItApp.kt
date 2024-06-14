package com.composeit

import android.app.Application
import com.composeit.alarm.di.alarmModule
import com.composeit.category.di.categoryModule
import com.composeit.di.appModule
import com.composeit.domain.di.domainModule
import com.composeit.glance.di.glanceModule
import com.composeit.local.di.localModule
import com.composeit.preference.di.preferenceModule
import com.composeit.repository.di.repositoryModule
import com.composeit.search.di.searchModule
import com.composeit.task.di.taskModule
import com.composeit.tracker.di.trackerModule
import com.composeitdatastore.di.dataStoreModule
import com.libraries.core.di.coreModule

import logcat.AndroidLogcatLogger
import logcat.LogPriority
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module


class ComposeItApp : Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ComposeItApp)
            modules(getAllModules())
        }
    }

    internal fun getAllModules(): List<Module> = listOf(
        appModule,
        coreModule,
        taskModule,
        alarmModule,
        categoryModule,
        searchModule,
        glanceModule,
        preferenceModule,
        domainModule,
        repositoryModule,
        localModule,
        dataStoreModule,
        trackerModule,
    )
}