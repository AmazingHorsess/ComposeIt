package com.composeit.alarm.notification

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import androidx.core.app.NotificationCompat
import com.composeit.alarm.R
import com.composeit.alarm.TaskReceiver
import com.composeit.alarm.model.Task
import com.composeit.alarmapi.AlarmPermission
import com.composeit.navigation.DestinationDeepLink
import com.libraries.core.extension.getNotificationManager
import kotlin.math.truncate

internal class TaskNotification(
    private val context: Context,
    private val channel: TaskNotificationChannel,
    private val alarmPermission: AlarmPermission,
) {

    /**
     * Shows the [TaskNotification] based on the given Task.
     *
     * @param task the task to be shown in the notification
     */
    fun show(task: Task) {
        val builder = buildNotification(task)
        builder.addAction(getCompleteAction(task))

        if (!alarmPermission.hasNotificationPermission()) {
            return
        }
        context.getNotificationManager()?.notify(task.id.toInt(), builder.build())
    }

    /**
     * Shows the repeating [TaskNotification] based on the given Task.
     *
     * @param task the task to be shown in the notification
     */
    fun showRepeating(task: Task) {
        val builder = buildNotification(task)

        if (!alarmPermission.hasNotificationPermission()) {
            return
        }
        context.getNotificationManager()?.notify(task.id.toInt(), builder.build())
    }

    /**
     * Dismiss the [TaskNotification] based on the given id.
     *
     * @param notificationId the notification id to be dismissed
     */
    fun dismiss(notificationId: Long) {
        context.getNotificationManager()?.cancel(notificationId.toInt())
    }

    private fun buildNotification(task: Task) =
        NotificationCompat.Builder(context, channel.getChannelId()).apply {
            setSmallIcon(com.google.android.material.R.drawable.mtrl_checkbox_button)
            setContentTitle(context.getString(R.string.content_app_name))
            setContentText(task.title)
            setContentIntent(buildPendingIntent(task))
            setAutoCancel(true)
            addAction(getSnoozeAction(task))
        }

    private fun buildPendingIntent(task: Task): PendingIntent {
        val openTaskIntent = Intent(
            Intent.ACTION_VIEW,
            DestinationDeepLink.getTaskDetailUri(task.id),
        )

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(openTaskIntent)
            getPendingIntent(
                REQUEST_CODE_OPEN_TASK,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
        }
    }

    private fun getCompleteAction(task: Task): NotificationCompat.Action {
        val actionTitle = context.getString(R.string.notification_action_completed)
        val intent = getIntent(task, TaskReceiver.COMPLETE_ACTION, REQUEST_CODE_ACTION_COMPLETE)
        return NotificationCompat.Action(ACTION_NO_ICON, actionTitle, intent)
    }

    private fun getSnoozeAction(task: Task): NotificationCompat.Action {
        val actionTitle = context.getString(R.string.notification_action_snooze)
        val intent = getIntent(task, TaskReceiver.SNOOZE_ACTION, REQUEST_CODE_ACTION_SNOOZE)
        return NotificationCompat.Action(ACTION_NO_ICON, actionTitle, intent)
    }

    private fun getIntent(
        task: Task,
        intentAction: String,
        requestCode: Int,
    ): PendingIntent {
        val receiverIntent = Intent(context, TaskReceiver::class.java).apply {
            action = intentAction
            putExtra(TaskReceiver.EXTRA_TASK, task.id)
        }

        return PendingIntent
            .getBroadcast(
                context,
                requestCode,
                receiverIntent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
    }

    companion object {

        private const val REQUEST_CODE_OPEN_TASK = 1_121_111

        private const val REQUEST_CODE_ACTION_COMPLETE = 1_234

        private const val REQUEST_CODE_ACTION_SNOOZE = 4_321

        private const val ACTION_NO_ICON = 0
    }
}