package com.composeit.presentation.model

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Composable
fun rememberComposeItAppState(windowSizeClass: WindowSizeClass): ComposeItAppState{
    return remember(windowSizeClass){
        ComposeItAppState(windowSizeClass)
    }
}

@Stable
data class ComposeItAppState(
    private val windowSizeClass: WindowSizeClass
){
    /**
     * Verifies if the bottom bar should be shown.
     */
    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    /**
     * Verifies if the navigation rail should be shown.
     */
    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar
}