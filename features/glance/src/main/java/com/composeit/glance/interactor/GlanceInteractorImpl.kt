package com.composeit.glance.interactor

import android.content.Context
import com.composeit.domain.interactor.GlanceInteractor
import com.composeit.glance.data.TaskListUpdaterWorker

internal class GlanceInteractorImpl(private val context:Context): GlanceInteractor {
    override suspend fun onTaskListUpdated() {
        TaskListUpdaterWorker.enqueue(context)
    }
}