package com.composeit.domain.di


import com.composeit.domain.provider.CalendarProvider
import com.composeit.domain.provider.CalendarProviderImpl
import com.composeit.domain.usecase.alarm.CancelAlarm
import com.composeit.domain.usecase.alarm.RescheduleFutureAlarms
import com.composeit.domain.usecase.alarm.ScheduleAlarm
import com.composeit.domain.usecase.alarm.ScheduleNextAlarm
import com.composeit.domain.usecase.alarm.ShowAlarm
import com.composeit.domain.usecase.alarm.SnoozeAlarm
import com.composeit.domain.usecase.alarm.UpdateTaskAsRepeating
import com.composeit.domain.usecase.alarm.implementation.CancelAlarmImpl
import com.composeit.domain.usecase.alarm.implementation.ScheduleAlarmImpl
import com.composeit.domain.usecase.alarm.implementation.UpdateTaskAsRepeatingImpl
import com.composeit.domain.usecase.category.AddCategory
import com.composeit.domain.usecase.category.DeleteCategory
import com.composeit.domain.usecase.category.LoadAllCategories
import com.composeit.domain.usecase.category.LoadCategory
import com.composeit.domain.usecase.category.UpdateCategory
import com.composeit.domain.usecase.category.implementation.AddCategoryImpl
import com.composeit.domain.usecase.category.implementation.DeleteCategoryImpl
import com.composeit.domain.usecase.category.implementation.LoadAllCategoriesImpl
import com.composeit.domain.usecase.category.implementation.LoadCategoryImpl
import com.composeit.domain.usecase.category.implementation.UpdateCategoryImpl
import com.composeit.domain.usecase.preferences.LoadAppTheme
import com.composeit.domain.usecase.preferences.UpdateAppTheme
import com.composeit.domain.usecase.search.SearchTaskByName
import com.composeit.domain.usecase.search.implementation.SearchTasksByNameImpl
import com.composeit.domain.usecase.task.AddTask
import com.composeit.domain.usecase.task.CompleteTask
import com.composeit.domain.usecase.task.DeleteTask
import com.composeit.domain.usecase.task.LoadTask
import com.composeit.domain.usecase.task.UncompleteTask
import com.composeit.domain.usecase.task.UpdateTask
import com.composeit.domain.usecase.task.UpdateTaskCategory
import com.composeit.domain.usecase.task.UpdateTaskDescription
import com.composeit.domain.usecase.task.UpdateTaskStatus
import com.composeit.domain.usecase.task.UpdateTaskTitle
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskCategoryImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskDescriptionImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskStatusImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskTitleImpl
import com.composeit.domain.usecase.taskwithcategory.LoadCompletedTasks
import com.composeit.domain.usecase.taskwithcategory.LoadUncompletedTasks
import com.composeit.domain.usecase.taskwithcategory.implementation.LoadUncompletedTasksImpl
import com.composeit.domain.usecase.tracker.LoadCompletedTasksByPeriod
import com.composeit.domain.usecase.tracker.implementation.LoadCompletedTasksByPeriodImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Domain dependency injection module.
 */
val domainModule = module {

    // Task Use Cases
    factoryOf(::AddTaskImpl) bind AddTask::class
    factoryOf(::CompleteTask)
    factoryOf(::UncompleteTask)
    factoryOf(::UpdateTaskStatusImpl) bind UpdateTaskStatus::class
    factoryOf(::DeleteTask)
    factoryOf(::LoadTaskImpl) bind LoadTask::class
    factoryOf(::UpdateTaskImpl) bind UpdateTask::class
    factoryOf(::UpdateTaskTitleImpl) bind UpdateTaskTitle::class
    factoryOf(::UpdateTaskDescriptionImpl) bind UpdateTaskDescription::class
    factoryOf(::UpdateTaskCategoryImpl) bind UpdateTaskCategory::class

    // Category Use Cases
    factoryOf(::DeleteCategoryImpl) bind DeleteCategory::class
    factoryOf(::LoadAllCategoriesImpl) bind LoadAllCategories::class
    factoryOf(::LoadCategoryImpl) bind LoadCategory::class
    factoryOf(::AddCategoryImpl) bind AddCategory::class
    factoryOf(::UpdateCategoryImpl) bind UpdateCategory::class

    // Search Use Cases
    factoryOf(::SearchTasksByNameImpl) bind SearchTaskByName::class

    // Task With Category Use Cases
    factoryOf(::LoadCompletedTasks)
    factoryOf(::LoadUncompletedTasksImpl) bind LoadUncompletedTasks::class

    // Alarm Use Cases
    factoryOf(::CancelAlarmImpl) bind CancelAlarm::class
    factoryOf(::RescheduleFutureAlarms)
    factoryOf(::ScheduleAlarmImpl) bind ScheduleAlarm::class
    factoryOf(::ScheduleNextAlarm)
    factoryOf(::ShowAlarm)
    factoryOf(::SnoozeAlarm)
    factoryOf(::UpdateTaskAsRepeatingImpl) bind UpdateTaskAsRepeating::class

    // Tracker Use Cases
    factoryOf(::LoadCompletedTasksByPeriodImpl) bind LoadCompletedTasksByPeriod::class

    // Preferences Use Cases
    factoryOf(::UpdateAppTheme)
    factoryOf(::LoadAppTheme)

    // Providers
    factoryOf(::CalendarProviderImpl) bind CalendarProvider::class
}