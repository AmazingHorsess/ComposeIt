package com.composeit.tracker.model

internal sealed class Tracker {

    internal data class Info(val categoryInfo : List<CategoryInfo>)

    internal data class CategoryInfo(
        val name: String?,
        val color: Int?,
        val taskCount: Int,
        val percentage: Float,
    )
}