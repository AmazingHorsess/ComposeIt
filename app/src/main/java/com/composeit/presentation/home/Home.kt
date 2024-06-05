package com.composeit.presentation.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.composeit.category.presentation.list.CategoryListSection
import com.composeit.design.ComposeItTheme
import com.composeit.model.HomeSection
import com.composeit.preference.presentation.PreferenceSection
import com.composeit.presentation.model.ComposeItAppState
import com.composeit.presentation.model.rememberComposeItAppState
import com.composeit.search.presentation.SearchSection
import com.composeit.task.presentation.list.TaskListSection

@Composable
fun Home(
    windowSizeClass: WindowSizeClass,
    onTaskClick: (Long) -> Unit,
    onAboutClick: () -> Unit,
    onTrackerClick: () -> Unit,
    onOpenSourceClick: () -> Unit,
    onTaskSheetOpen: () -> Unit,
    onCategorySheetOpen: (Long?) -> Unit,
) {
    val appState = rememberComposeItAppState(windowSizeClass = windowSizeClass)
    val (currentSection,setCurrentSection) = rememberSaveable {
        mutableStateOf(HomeSection.Tasks)
    }
    val navItems = HomeSection.entries.toList()

    val actions = HomeActions(
        onTaskClick = onTaskClick,
        onAboutClick = onAboutClick,
        onTrackerClick = onTrackerClick,
        onOpenSourceClick = onOpenSourceClick,
        onTaskSheetOpen = onTaskSheetOpen,
        onCategorySheetOpen = onCategorySheetOpen,
        setCurrentSection = setCurrentSection
    )
    Crossfade(currentSection) { homeSection ->
        ComposeItHomeScaffold(homeSection = homeSection, navItems = navItems , actions = actions , appState = appState )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComposeItTopAppBar(
    currentSection: HomeSection
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Light),
                text = currentSection.title,
                color = MaterialTheme.colorScheme.tertiary,
            )
        },
    )
}

@Composable
private fun ComposeItBottomBar(
    currentSection: HomeSection,
    onSectionSelect: (HomeSection) -> Unit,
    items: List<HomeSection>,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach{ section ->
            val selected = section == currentSection
            val title = section.title
            NavigationBarItem(
                selected = selected,
                onClick = { onSectionSelect(section) },
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = title
                    )
                },
                label = { Text(text = title)}
            )
        }
    }
}
@Composable
private fun ComposeItHomeScaffold(
    homeSection: HomeSection,
    navItems: List<HomeSection>,
    actions: HomeActions,
    appState: ComposeItAppState
){
    Scaffold (
        topBar = {
            ComposeItTopAppBar(currentSection = homeSection)
        },
        contentWindowInsets = WindowInsets(0,0,0,0),
        content = {paddingValues ->
                  Row(
                      modifier = Modifier
                          .fillMaxSize()
                          .padding(paddingValues)
                          .consumeWindowInsets(paddingValues)
                          .windowInsetsPadding(
                              WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                          ),

                  ){
                      if (appState.shouldShowNavRail){
                          ComposeItNavRail(
                              currentSection = homeSection,
                              onSectionSelect = actions.setCurrentSection,
                              items = navItems,
                              modifier = Modifier.safeDrawingPadding(),
                          )

                      }
                      Column(
                          modifier = Modifier
                              .fillMaxSize()
                      ) {
                          ComposeItContent(
                              homeSection = homeSection,
                              modifier = Modifier,
                              actions = actions
                          )
                      }
                  }

        },
        bottomBar = {
            ComposeItBottomBar(
                currentSection = homeSection,
                onSectionSelect = actions.setCurrentSection,
                items = navItems,
                modifier = Modifier.safeDrawingPadding(),
            )
        }
    )

}
@Composable
private fun ComposeItContent(
    homeSection: HomeSection,
    actions: HomeActions,
    modifier: Modifier = Modifier
){
    when(homeSection){
        HomeSection.Tasks ->
            TaskListSection(
                modifier = modifier,
                onItemClick = actions.onTaskClick,
                onBottomShow = actions.onTaskSheetOpen,
            )

        HomeSection.Search ->
            SearchSection(modifier = modifier, onItemClick = actions.onTaskClick)

        HomeSection.Categories ->
            CategoryListSection(
                modifier = modifier,
                onShowBottomSheet = actions.onCategorySheetOpen,
            )

        HomeSection.Settings ->
            PreferenceSection(
                modifier = modifier,
                onAboutClick = actions.onAboutClick,
                onTrackerClick = actions.onTrackerClick,
                onOpenSourceClick = actions.onOpenSourceClick,
            )
    }

}
@Composable
fun EmptyComposable(){

}
@Composable
private fun ComposeItNavRail(
    currentSection: HomeSection,
    onSectionSelect: (HomeSection) -> Unit,
    items: List<HomeSection>,
    modifier: Modifier = Modifier
){
    NavigationRail(
        modifier = modifier
    ) {
        items.forEach { section ->
            val selected = section == currentSection
            NavigationRailItem(
                selected = selected,
                onClick = { onSectionSelect(section) },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = "${section.name} navigation rail section"
                    )
                },
                label = {
                    Text(text = section.title)
                },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )

            )
        }

    }

}

// Previews
@Preview(showBackground = true)
@Composable
private fun ComposeItNavRailTasksPreview(){
    ComposeItTheme {
        ComposeItNavRail(
            currentSection = HomeSection.Tasks,
            onSectionSelect ={} ,
            items = HomeSection.entries.toList()
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItNavRailSearchPreview(){
    ComposeItTheme {
        ComposeItNavRail(
            currentSection = HomeSection.Search,
            onSectionSelect ={} ,
            items = HomeSection.entries.toList()
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItNavRailCategoriesPreview(){
    ComposeItTheme {
        ComposeItNavRail(
            currentSection = HomeSection.Categories,
            onSectionSelect ={} ,
            items = HomeSection.entries.toList()
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItNavRailSettingsPreview(){
    ComposeItTheme {
        ComposeItNavRail(
            currentSection = HomeSection.Settings,
            onSectionSelect ={} ,
            items = HomeSection.entries.toList()
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItTopAppBarPreview(){
    ComposeItTheme {
        ComposeItTopAppBar(currentSection = HomeSection.Tasks)
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItBottomNavTasksPreview() {
    ComposeItTheme {
        ComposeItBottomBar(
            currentSection = HomeSection.Tasks,
            onSectionSelect = {},
            items = HomeSection.entries.toList(),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ComposeItBottomNavSearchPreview() {
    ComposeItTheme {
        ComposeItBottomBar(
            currentSection = HomeSection.Search,
            onSectionSelect = {},
            items = HomeSection.entries.toList(),
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItBottomNavCategoriesPreview() {
    ComposeItTheme {
        ComposeItBottomBar(
            currentSection = HomeSection.Categories,
            onSectionSelect = {},
            items = HomeSection.entries.toList(),
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ComposeItBottomNavSettingsPreview() {
    ComposeItTheme {
        ComposeItBottomBar(
            currentSection = HomeSection.Settings,
            onSectionSelect = {},
            items = HomeSection.entries.toList(),
        )
    }
}