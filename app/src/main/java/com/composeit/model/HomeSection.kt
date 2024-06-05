package com.composeit.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeSection(
    val title:String,
    val icon:ImageVector
) {
    Tasks(
        title = "Tasks",
        icon = Icons.Outlined.Check
    ),

    Search(
    title = "Search",
    icon = Icons.Outlined.Search
    ),

    Categories(
    title = "Categories",
    icon = Icons.Outlined.Bookmark
    ),

    Settings(
    title = "Settings",
    icon = Icons.Outlined.MoreHoriz
    ),

}