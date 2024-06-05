package com.composeit.task.presentation.detail

import com.composeit.task.model.AlarmInterval
import com.composeit.task.presentation.detail.main.CategoryId
import java.util.Calendar

internal data class TaskDetailActions(
    val onTitleChange: (String) -> Unit,
    val onDescriptionChange: (String) -> Unit,
    val onCategoryChange: (CategoryId) -> Unit,
    val onAlarmUpdate: (Calendar?) -> Unit,
    val onIntervalSelect: (AlarmInterval) -> Unit,
    val hasAlarmPermission: () -> Boolean = { false },
    val shouldCheckNotificationPermission: Boolean = false,
    val onUpPress: () -> Unit,
)
