package com.composeit.tracker.mapper

import android.graphics.Color
import com.composeit.domain.model.TaskWithCategory
import com.composeit.tracker.model.Tracker

internal class TrackerMapper {

    internal fun toTracker(list: List<TaskWithCategory>): Tracker.Info {
        val count = list.count()
        val categories = list.groupBy { task -> task.category?.id }.map { toCategory(it, count) }
        return Tracker.Info(categories.toList())
    }

    private fun toCategory(
        map: Map.Entry<Long?, List<TaskWithCategory>>,
        totalCount: Int,
    ): Tracker.CategoryInfo {
        val first = map.value.first()
        val taskCount = map.value.size
        return Tracker.CategoryInfo(
            name = first.category?.name,
            color = first.category?.color?.let { color -> Color.parseColor(color) },
            taskCount = taskCount,
            percentage = taskCount.toFloat() / totalCount,
        )
    }
}