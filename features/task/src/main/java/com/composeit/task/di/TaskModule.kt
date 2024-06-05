package com.composeit.task.di

import com.composeit.task.mapper.AlarmIntervalMapper
import com.composeit.task.mapper.CategoryMapper
import com.composeit.task.mapper.TaskMapper
import com.composeit.task.mapper.TaskWithCategoryMapper
import com.composeit.task.presentation.add.AddTaskViewModel
import com.composeit.task.presentation.detail.alarm.TaskAlarmViewModel
import com.composeit.task.presentation.detail.main.TaskDetailViewModel
import com.composeit.task.presentation.list.TaskListViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.factoryOf
import org.koin.androidx.viewmodel.dsl.viewModelOf

/**
 * Task dependency injection module.
 */
val taskModule = module {

    // Presentation
    viewModelOf(::TaskListViewModel)
    viewModelOf(::TaskDetailViewModel)
    viewModelOf(::TaskAlarmViewModel)
    viewModelOf(::AddTaskViewModel)

    // Mappers
    factoryOf(::AlarmIntervalMapper)
    factoryOf(::TaskMapper)
    factoryOf(::TaskWithCategoryMapper)
    factoryOf(::CategoryMapper)
}