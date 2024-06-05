package com.composeit.domain.usecase.fake

import com.composeit.domain.interactor.NotificationInteractor
import com.composeit.domain.model.Task

internal class NotificationInteractorFake: NotificationInteractor {
    private val notificationMap: MutableMap<Long, Boolean> = mutableMapOf()
    override fun show(task: Task) {
        notificationMap[task.id] = true
    }

    override fun dismiss(notificationId: Long) {
        notificationMap[notificationId] = false
    }

    fun isNotificationShown(notificationId: Long): Boolean =
        notificationMap[notificationId] ?: false


    fun clear() {
        notificationMap.clear()
    }
}