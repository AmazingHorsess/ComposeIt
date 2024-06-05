package com.composeit.domain.usecase.alarm

import com.composeit.domain.model.AlarmInterval

interface UpdateTaskAsRepeating {

    suspend operator fun invoke(taskId: Long, interval: AlarmInterval?)

}