package com.composeit.task.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amazinghorsess.category_api.model.Category
import com.amazinghorsess.category_api.presentation.CategoryListViewModel
import com.amazinghorsess.category_api.presentation.CategoryState
import com.amazinghorsess.task.R
import com.composeit.design.ComposeItTheme
import com.composeit.design.components.ComposeItFloatingButton
import com.composeit.design.components.ComposeItLoadingContent
import com.composeit.design.components.DefaultIconTextContent
import com.composeit.task.model.Task
import com.composeit.task.model.TaskWithCategory
import com.composeit.task.presentation.category.CategorySelection
import com.composeit.task.presentation.detail.main.CategoryId
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@Composable
fun TaskListSection(
    onItemClick: (Long) -> Unit,
    onBottomShow: () -> Unit,
    modifier: Modifier = Modifier
){
    TaskListLoader(onItemClick = onItemClick, onAddClick = onBottomShow, modifier = modifier )

}


@Composable
private fun TaskListLoader(
    onItemClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    taskListViewModel: TaskListViewModel = koinViewModel(),
    categoryViewModel: CategoryListViewModel = koinViewModel(),
    ){
    val (currentCategory, setCategory) = rememberSaveable{ mutableStateOf<CategoryId?>(null) }

    val taskViewState by remember(taskListViewModel,currentCategory){
        taskListViewModel.loadAllTask(currentCategory?.value)
    }.collectAsState(initial = TaskListViewState.Loading)

    val categoryViewState by remember(categoryViewModel) {
        categoryViewModel.loadCategories()
    }.collectAsState(initial = CategoryState.Loading)

    val taskHandler = TaskStateHandler(
        state = taskViewState,
        onCheckedChange = taskListViewModel::updateTaskStatus,
        onItemClick = onItemClick,
        onAddClick = onAddClick
    )

    val categoryHandler = CategoryStateHandler(
        state = categoryViewState,
        currentCategory = currentCategory,
        onCategoryChange = setCategory,
    )

    TaskListScaffold(
        taskHandler = taskHandler,
        categoryHandler = categoryHandler,
        modifier = modifier
    )

}


@Composable
internal fun TaskListScaffold(
    taskHandler: TaskStateHandler,
    categoryHandler: CategoryStateHandler,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val snackbarTitle = stringResource(id = R.string.task_snackbar_message_complete)
    val snackbarButton = stringResource(id = R.string.task_snackbar_button_undo)

    val onShowSnackbar: (TaskWithCategory) -> Unit = { taskWithCategory ->
        coroutineScope.launch {
            val message = String.format(snackbarTitle, taskWithCategory.task.title)
            val snackbarResult = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = snackbarButton,
                duration = SnackbarDuration.Short,
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> {} // Do nothing
                SnackbarResult.ActionPerformed -> taskHandler.onCheckedChange(taskWithCategory)
            }
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TaskFilter(categoryHandler = categoryHandler)},
        floatingActionButton = {
            ComposeItFloatingButton(
                onClick = { taskHandler.onAddClick() },
                contentDescription = stringResource(R.string.task_cd_add_task)
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ){ paddingValues ->
        Crossfade(
            targetState = taskHandler.state,
            modifier = Modifier.padding(paddingValues),
        ) {state ->
            when(state) {
                TaskListViewState.Loading -> ComposeItLoadingContent()
                is TaskListViewState.Error -> TaskListError()
                is TaskListViewState.Loaded -> {
                    val taskList = state.items
                    TaskListContent(
                        taskList = taskList,
                        onItemClick = taskHandler.onItemClick,
                        onCheckedChange = { taskWithCategory ->
                            taskHandler.onCheckedChange(taskWithCategory)
                            onShowSnackbar(taskWithCategory)
                        },
                    )
                }
                TaskListViewState.Empty -> TaskListEmpty()
            }

            
        }


    }



}


@Composable
private fun TaskFilter(
    categoryHandler: CategoryStateHandler,
){
    CategorySelection(
        state = categoryHandler.state ,
        currentCategory = categoryHandler.currentCategory?.value ,
        onCategoryChange = categoryHandler.onCategoryChange,
        modifier = Modifier.padding(start = 14.dp)
    )
}

@Composable
private fun TaskListContent(
    taskList: List<TaskWithCategory>,
    onItemClick: (Long) -> Unit,
    onCheckedChange: (TaskWithCategory) -> Unit,
){
    LazyColumn(
        contentPadding = PaddingValues(bottom = 46.dp),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        items(
            items = taskList,
            itemContent = { task ->
                TaskItem(
                    task = task,
                    onItemClick = onItemClick,
                    onCheckedChange =  onCheckedChange
                )
            }

        )

    }
}


@Composable
private fun TaskListEmpty(){
    DefaultIconTextContent(
        icon = Icons.Outlined.ThumbUp ,
        text = R.string.task_list_cd_empty_list ,
        iconContentDescription = R.string.task_list_header_empty
    )
}

@Composable
private fun TaskListError(){
    DefaultIconTextContent(
        icon = Icons.Outlined.Close ,
        text = R.string.task_list_header_error ,
        iconContentDescription = R.string.task_list_cd_error
    )
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun TaskListScaffoldLoaded() {
    val task1 = Task(title = "Buy milk", dueDate = null)
    val task2 = Task(title = "Call Mark", dueDate = Calendar.getInstance())
    val task3 = Task(title = "Watch Moonlight", dueDate = Calendar.getInstance())

    val category1 = Category(name = "Books", color = android.graphics.Color.GREEN)
    val category2 = Category(name = "Reminders", color = android.graphics.Color.MAGENTA)

    val taskList = listOf(
        TaskWithCategory(task = task1, category = category1),
        TaskWithCategory(task = task2, category = category2),
        TaskWithCategory(task = task3, category = null),
    )

    val state = TaskListViewState.Loaded(items = taskList)

    ComposeItTheme {
        TaskListScaffold(
            taskHandler = TaskStateHandler(
                state = state,
                onAddClick = {},
                onItemClick = { },
            ),
            categoryHandler = CategoryStateHandler(),
            modifier = Modifier,
        )
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun TaskListScaffoldEmpty() {
    val state = TaskListViewState.Empty

    ComposeItTheme {
        TaskListScaffold(
            taskHandler = TaskStateHandler(
                state = state,
                onAddClick = {},
                onItemClick = { },
            ),
            categoryHandler = CategoryStateHandler(),
            modifier = Modifier,
        )
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun TaskListScaffoldError() {
    val state = TaskListViewState.Error(cause = IllegalAccessException())

    ComposeItTheme {
        TaskListScaffold(
            taskHandler = TaskStateHandler(
                state = state,
                onAddClick = {},
                onItemClick = { },
                ),
            categoryHandler = CategoryStateHandler(),
            modifier = Modifier,
        )
    }
}
