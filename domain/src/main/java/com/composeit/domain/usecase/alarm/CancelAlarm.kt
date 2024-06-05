package com.composeit.domain.usecase.alarm

interface CancelAlarm {

    suspend operator fun invoke(taskId: Long)
}