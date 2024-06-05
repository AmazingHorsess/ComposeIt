package com.composeit.alarm.di

import com.composeit.alarm.interactor.AlarmInteractorImpl
import com.composeit.alarm.interactor.NotificationInteractorImpl
import com.composeit.alarm.mapper.TaskMapper
import com.composeit.alarm.notification.TaskNotification
import com.composeit.alarm.notification.TaskNotificationChannel
import com.composeit.alarm.notification.TaskNotificationScheduler
import com.composeit.alarm.permission.AlarmPermissionImpl
import com.composeit.alarm.permission.AndroidVersion
import com.composeit.alarm.permission.AndroidVersionImpl
import com.composeit.alarm.permission.PermissionChecker
import com.composeit.alarm.permission.PermissionCheckerImpl
import com.composeit.alarmapi.AlarmPermission
import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.interactor.NotificationInteractor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val alarmModule = module {
    factoryOf(::TaskNotificationScheduler)
    factoryOf(::TaskNotificationChannel)
    factoryOf(::TaskNotification)

    factoryOf(::TaskMapper)

    factoryOf(::AlarmInteractorImpl) bind AlarmInteractor::class
    factoryOf(::NotificationInteractorImpl) bind NotificationInteractor::class

    factoryOf(::AndroidVersionImpl) bind AndroidVersion::class
    factoryOf(::PermissionCheckerImpl) bind PermissionChecker::class
    factoryOf(::AlarmPermissionImpl) bind AlarmPermission::class

}