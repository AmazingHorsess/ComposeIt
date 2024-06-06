package com.composeit.navigation

import android.content.Context
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.composeit.category.presentation.bottomsheet.CategoryBottomSheet
import com.composeit.preference.presentation.About
import com.composeit.preference.presentation.OpenSource
import com.composeit.presentation.home.EmptyComposable
import com.composeit.presentation.home.Home
import com.composeit.task.presentation.add.AddTaskBottomSheet
import com.composeit.task.presentation.detail.main.TaskDetailSection
import com.composeit.tracker.presentation.TrackerSection
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun NavGraph(
    windowSizeClass: WindowSizeClass,
    startDestination: String = Destinations.Home
){
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)
    val context = LocalContext.current
    val actions = remember(navController){Actions(navController, context)}
    
    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ){
            homeGraph(windowSizeClass, actions)
            taskGraph(actions)
            preferencesGraph(actions)
            categoryGraph(actions)
        }
        
    }
}

private fun NavGraphBuilder.homeGraph(windowSizeClass: WindowSizeClass, actions: Actions) {
    composable(
        route = Destinations.Home,
        deepLinks = listOf(navDeepLink { uriPattern = DestinationDeepLink.HomePattern }),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700),
            )
        },
    ) {
        Home(
            windowSizeClass = windowSizeClass,
            onTaskClick = actions.openTaskDetail,
            onAboutClick = actions.openAbout,
            onTrackerClick = actions.openTracker,
            onOpenSourceClick = actions.openOpenSource,
            onTaskSheetOpen = actions.openTaskBottomSheet,
            onCategorySheetOpen = actions.openCategoryBottomSheet,
        )
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.taskGraph(actions: Actions) {
    composable(
        route = "${Destinations.TaskDetail}/{${DestinationArgs.TaskId}}",
        arguments = listOf(navArgument(DestinationArgs.TaskId) { type = NavType.LongType }),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = DestinationDeepLink.TaskDetailPattern
            },
        ),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700),
            )
        },
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        TaskDetailSection(
            taskId = arguments.getLong(DestinationArgs.TaskId),
            onUpPress = actions.navigateUp,
        )
    }

    bottomSheet(Destinations.BottomSheet.Task) {
        AddTaskBottomSheet(actions.navigateUp)
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Suppress("MagicNumber")
private fun NavGraphBuilder.categoryGraph(actions: Actions) {
    bottomSheet(
        route = "${Destinations.BottomSheet.Category}/{${DestinationArgs.CategoryId}}",
        arguments = listOf(
            navArgument(DestinationArgs.CategoryId) {
                type = NavType.LongType
            },
        ),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = DestinationDeepLink.CategorySheetPattern
            },
        ),
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getLong(DestinationArgs.CategoryId) ?: 0L
        CategoryBottomSheet(
            categoryId = id,
            onHideBottomSheet = actions.navigateUp,
        )
    }
}

@Suppress("MagicNumber")
private fun NavGraphBuilder.preferencesGraph(actions: Actions) {
    composable(route = Destinations.About) {
        About(onUpPress = actions.navigateUp)
    }
    composable(route = Destinations.OpenSource) {
        OpenSource(onUpPress = actions.navigateUp)
    }
    composable(route = Destinations.Tracker) {
        TrackerSection(
            onUpPress = actions.navigateUp
        )


    }

}

private fun NavGraphBuilder.trackerGraph(
    context: Context,
    actions: Actions,
) {


}



internal data class Actions (
    val navController: NavHostController,
    val context: Context
){
    val openTaskDetail: (Long) -> Unit = { taskId ->
        navController.navigate("${Destinations.TaskDetail}/$taskId")
    }
    val openOpenSource: () -> Unit = {
        navController.navigate(Destinations.OpenSource)
    }

    val openAbout: () -> Unit = {
        navController.navigate(Destinations.About)
    }

    val openTracker: () -> Unit = {
        navController.navigate(Destinations.Tracker)
    }

    val openTaskBottomSheet: () -> Unit = {
        navController.navigate(Destinations.BottomSheet.Task)
    }

    val openCategoryBottomSheet: (Long?) -> Unit = { categoryId ->
        val id = categoryId ?: 0L
        navController.navigate("${Destinations.BottomSheet.Category}/$id")
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

}