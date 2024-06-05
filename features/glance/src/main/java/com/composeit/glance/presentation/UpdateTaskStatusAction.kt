package com.composeit.glance.presentation

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import com.composeit.domain.interactor.GlanceInteractor
import com.composeit.glance.data.TaskListGlanceUpdater
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class UpdateTaskStatusAction : ActionCallback, KoinComponent {

    private val viewModel: TaskListGlanceUpdater by inject()

    private val glanceInteractor: GlanceInteractor by inject()

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val taskId = parameters[TaskIdKey]?.toLong() ?: return
        viewModel.updateTaskAsCompleted(taskId)
        glanceInteractor.onTaskListUpdated()
    }
}

internal val TaskIdKey = ActionParameters.Key<String>("TaskId")