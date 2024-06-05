package com.composeit.alarm.interactor

import android.app.NotificationManager
import com.composeit.alarm.mapper.TaskMapper
import com.composeit.alarm.notification.TaskNotification
import com.composeit.domain.interactor.NotificationInteractor
import com.composeit.domain.model.Task

internal class NotificationInteractorImpl(
    private val taskNotification: TaskNotification,
    private val taskMapper: TaskMapper,
): NotificationInteractor{
    override fun show(task: Task) {
        if (task.isRepeating){
            taskNotification.showRepeating(taskMapper.toView(task))
        } else {
            taskNotification.show(taskMapper.toView(task))
        }
    }

    override fun dismiss(notificationId: Long) {
        taskNotification.dismiss(notificationId)
    }
}