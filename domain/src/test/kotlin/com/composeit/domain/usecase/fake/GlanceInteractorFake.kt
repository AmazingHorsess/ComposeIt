package com.composeit.domain.usecase.fake

import com.composeit.domain.interactor.GlanceInteractor

internal class GlanceInteractorFake: GlanceInteractor {
    var wasNotified: Boolean = false
    override suspend fun onTaskListUpdated() {
        wasNotified = true

    }
    fun clean(){
        wasNotified = false
    }
}