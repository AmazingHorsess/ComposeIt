package com.composeit.task.presentation.detail.main

import android.graphics.Color
import android.os.Parcelable
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.amazinghorsess.category_api.model.Category
import com.amazinghorsess.category_api.presentation.CategoryListViewModel
import com.amazinghorsess.category_api.presentation.CategoryState
import com.amazinghorsess.task.R
import com.composeit.alarmapi.AlarmPermission
import com.composeit.design.ComposeItTheme
import com.composeit.design.components.ComposeItLoadingContent
import com.composeit.design.components.ComposeItTopAppBar
import com.composeit.design.components.DefaultIconTextContent
import com.composeit.task.model.Task
import com.composeit.task.presentation.category.CategorySelection
import com.composeit.task.presentation.detail.LeadingIcon
import com.composeit.task.presentation.detail.TaskDetailActions
import com.composeit.task.presentation.detail.TaskDetailSectionContent
import com.composeit.task.presentation.detail.alarm.AlarmSelection
import com.composeit.task.presentation.detail.alarm.TaskAlarmViewModel
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun TaskDetailSection(taskId: Long, onUpPress: () -> Unit) {
    TaskDetailLoader(taskId = taskId, onUpPress = onUpPress)
}

@Composable
private fun TaskDetailLoader(
    taskId: Long,
    onUpPress: () -> Unit,
    detailViewModel: TaskDetailViewModel = koinViewModel(),
    categoryViewModel: CategoryListViewModel = koinViewModel(),
    alarmViewModel: TaskAlarmViewModel = koinViewModel(),
    alarmPermission: AlarmPermission = koinInject(),
) {
    val id = TaskId(taskId)
    val detailViewState by
    remember(detailViewModel, taskId) {
        detailViewModel.loadTaskInfo(taskId = id)
    }.collectAsState(initial = TaskDetailState.Loading)

    val categoryViewState by
    remember(categoryViewModel, taskId) {
        categoryViewModel.loadCategories()
    }.collectAsState(initial = CategoryState.Loading)

    val taskDetailActions = TaskDetailActions(
        onTitleChange = { title -> detailViewModel.updateTitle(id, title) },
        onDescriptionChange = { desc -> detailViewModel.updateDescription(id, desc) },
        onCategoryChange = { categoryId -> detailViewModel.updateCategory(id, categoryId) },
        onAlarmUpdate = { calendar -> alarmViewModel.updateAlarm(id, calendar) },
        onIntervalSelect = { interval -> alarmViewModel.setRepeating(id, interval) },
        hasAlarmPermission = { alarmPermission.hasExactAlarmPermission() },
        shouldCheckNotificationPermission = alarmPermission.shouldCheckNotificationPermission(),
        onUpPress = onUpPress,
    )

    TaskDetailRouter(
        detailViewState = detailViewState,
        categoryViewState = categoryViewState,
        actions = taskDetailActions,
    )
}

@Composable
internal fun TaskDetailRouter(
    detailViewState: TaskDetailState,
    categoryViewState: CategoryState,
    actions: TaskDetailActions,
) {
    Scaffold(
        topBar = {
            ComposeItTopAppBar(onPress = actions.onUpPress)
        }) { paddingValues ->
        Crossfade(
            targetState = detailViewState,
            modifier = Modifier.padding(paddingValues),
        ) { state ->
            when (state) {
                TaskDetailState.Loading -> ComposeItLoadingContent()
                TaskDetailState.Error -> TaskDetailError()
                is TaskDetailState.Loaded ->
                    TaskDetailContent(
                        task = state.task,
                        categoryViewState = categoryViewState,
                        actions = actions,
                    )
            }
        }
    }
}


@Composable
private fun TaskDetailContent(
    task: Task,
    categoryViewState: CategoryState,
    actions: TaskDetailActions,
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            TaskTitleTextField(text = task.title, onTitleChange = actions.onTitleChange)
            TaskDetailSectionContent(
                imageVector = Icons.Outlined.Bookmark,
                contentDescription = R.string.task_detail_cd_icon_category,
            ) {
                CategorySelection(
                    state = categoryViewState,
                    currentCategory = task.categoryId,
                    onCategoryChange = actions.onCategoryChange,
                )
            }
            TaskDescriptionTextField(
                text = task.description,
                onDescriptionChange = actions.onDescriptionChange,
            )
            AlarmSelection(
                calendar = task.dueDate,
                interval = task.alarmInterval,
                onAlarmUpdate = actions.onAlarmUpdate,
                onIntervalSelect = actions.onIntervalSelect,
                hasAlarmPermission = actions.hasAlarmPermission,
                shouldCheckNotificationPermission = actions.shouldCheckNotificationPermission,
            )
        }
    }
}

@Composable
private fun TaskDetailError() {
    DefaultIconTextContent(
        icon = Icons.Outlined.Close,
        iconContentDescription = R.string.task_detail_cd_error,
        text = R.string.task_detail_header_error,
    )
}

@Composable
private fun TaskTitleTextField(text: String, onTitleChange: (String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue(text)) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState.value,
        onValueChange = {
            onTitleChange(it.text)
            textState.value = it
        },
        textStyle = MaterialTheme.typography.headlineMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
        ),
    )
}

@Composable
private fun TaskDescriptionTextField(
    text:String?,
    onDescriptionChange: (String) -> Unit,
){
    val textState = remember {
        mutableStateOf(TextFieldValue(text ?:""))
    }
    
    TextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
                      LeadingIcon(
                          imageVector = Icons.Default.Description,
                          contentDescription = R.string.task_detail_cd_icon_description,
                      )
        },
        value = textState.value,
        onValueChange ={
        onDescriptionChange(it.text)
        textState.value = it
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
        )
    )
}
@Suppress("ModifierOrder")
@JvmInline
@Parcelize
internal value class CategoryId(val value: Long?) : Parcelable

@JvmInline
@Parcelize
internal value class TaskId(val value: Long) : Parcelable


@Preview
@Composable
fun TaskDetailPreview() {
    val task = Task(title = "Buy milk", description = "This is a amazing task!", dueDate = null)
    val category1 = Category(name = "Groceries", color = Color.CYAN)
    val category2 = Category(name = "Books", color = android.graphics.Color.RED)
    val category3 = Category(name = "Movies", color = android.graphics.Color.MAGENTA)

    val categories = listOf(category1, category2, category3)

    ComposeItTheme {

        TaskDetailContent(
            task = task,
            categoryViewState = CategoryState.Loaded(categories),
            actions = TaskDetailActions(
                onCategoryChange = {},
                onUpPress = {},
                onIntervalSelect = {},
                onAlarmUpdate = {},
                onTitleChange = {},
                onDescriptionChange = {},
            ),
        )
    }
}
