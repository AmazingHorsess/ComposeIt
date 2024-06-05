package com.composeit.domain.interactor

import com.composeit.domain.model.Task

interface NotificationInteractor {

    fun show(task:Task)

    fun dismiss(notificationId: Long)

}