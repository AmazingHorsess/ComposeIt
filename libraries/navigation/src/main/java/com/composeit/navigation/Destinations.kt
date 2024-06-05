package com.composeit.navigation

import android.net.Uri
import androidx.core.net.toUri


object Destinations {

    const val Home = "home"

    const val TaskDetail = "task_detail"

    const val About = "about"

    const val Tracker = "tracker"

    object BottomSheet {

        const val Category = "bottom_sheet_category"

        const val Task = "bottom_sheet_task"
    }
}
object DestinationArgs {

    /**
     * Argument to be passed to [Destinations.TaskDetail] representing the task id to be detailed.
     */
    const val TaskId = "task_id"

    /**
     * Argument to be passed to [Destinations.BottomSheet.Category] representing the category id to
     * be detailed.
     */
    const val CategoryId = "category_id"
}
object DestinationDeepLink {

    private val BaseUri = "app://com.composeit".toUri()

    /**
     * Deep link pattern to be registered in [Destinations.Home] composable.
     */
    val HomePattern = "$BaseUri/home"

    /**
     * Deep link pattern to be registered in [Destinations.TaskDetail] composable.
     */
    val TaskDetailPattern = "$BaseUri/${DestinationArgs.TaskId}={${DestinationArgs.TaskId}}"

    /**
     * Deep link pattern to be registered in [Destinations.BottomSheet.Category] composable.
     */
    val CategorySheetPattern =
        "$BaseUri/${DestinationArgs.CategoryId}={${DestinationArgs.CategoryId}}"

    /**
     * Returns the [Destinations.TaskDetail] deep link with the argument set.
     *
     * @return the [Destinations.TaskDetail] deep link with the argument set
     */
    fun getTaskDetailUri(taskId: Long): Uri =
        "$BaseUri/${DestinationArgs.TaskId}=$taskId".toUri()

    /**
     * Returns the [Destinations.Home] deep link with the argument set.
     *
     * @return the [Destinations.Home] deep link with the argument set
     */
    fun getTaskHomeUri(): Uri =
        HomePattern.toUri()
}