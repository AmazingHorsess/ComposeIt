package com.composeit.tracker.presentation

import com.composeit.tracker.model.Tracker

internal sealed class TrackerViewState {

    internal data object Loading: TrackerViewState()

    internal data class Loaded(val trackerInfo: Tracker.Info): TrackerViewState()

    internal data object Empty: TrackerViewState()

    internal data class Error(val error:Throwable): TrackerViewState()
}