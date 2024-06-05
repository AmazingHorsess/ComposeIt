package com.composeit.glance.data

import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.composeit.glance.presentation.TaskListGlanceWidget

internal class TaskListGlanceReceiver: GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = TaskListGlanceWidget()

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        TaskListUpdaterWorker.cancel(context)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        TaskListUpdaterWorker.enqueue(context)    }
}