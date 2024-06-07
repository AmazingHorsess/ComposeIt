package com.composeit.tracker.presentation

import androidx.lifecycle.ViewModel
import com.composeit.domain.usecase.taskwithcategory.LoadCompletedTasks
import com.composeit.domain.usecase.tracker.LoadCompletedTasksByPeriod
import com.composeit.tracker.mapper.TrackerMapper
import com.composeit.tracker.model.Tracker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class TrackerViewModel(
    private val loadCompletedTaskByPeriod: LoadCompletedTasksByPeriod,
    private val trackerMapper: TrackerMapper,
): ViewModel() {
    fun loadTracker(): Flow<TrackerViewState> = flow {
        loadCompletedTaskByPeriod()
            .map {  task -> trackerMapper.toTracker(task) }
            .catch { error ->  emit(TrackerViewState.Error(error))
                error.printStackTrace()}
            .collect{ trackerInfo ->
                val state = if (trackerInfo.categoryInfo.isNotEmpty()){
                    TrackerViewState.Loaded(trackerInfo)
                } else {
                    TrackerViewState.Empty
                }
                emit(state)
            }
    }
}