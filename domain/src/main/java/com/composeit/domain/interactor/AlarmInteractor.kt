package com.composeit.domain.interactor

interface AlarmInteractor {

    fun schedule(alarmId: Long, timeMillis: Long)

    fun cancel(alarmId: Long)
}