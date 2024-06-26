package com.composeit.task.presentation.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amazinghorsess.category_api.presentation.CategoryListViewModel
import com.amazinghorsess.category_api.presentation.CategoryState
import com.amazinghorsess.task.R
import com.composeit.design.ComposeItTheme
import com.composeit.design.components.ComposeItTextField
import com.composeit.task.presentation.category.CategorySelection
import com.composeit.task.presentation.detail.main.CategoryId
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
@Composable
fun AddTaskBottomSheet(onHideBottomSheet: () -> Unit) {
    AddTaskLoader(onHideBottomSheet = onHideBottomSheet)
}
@Composable
internal fun AddTaskLoader(
    addTaskViewModel: AddTaskViewModel = koinViewModel(),
    categoryViewModel: CategoryListViewModel = koinViewModel(),
    onHideBottomSheet: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .background(MaterialTheme.colorScheme.surface) // Accompanist does not support M3 yet
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        var taskInputText by rememberSaveable { mutableStateOf("") }
        val categoryState by remember(categoryViewModel) {
            categoryViewModel
        }.loadCategories().collectAsState(initial = CategoryState.Empty)
        var currentCategory by rememberSaveable { mutableStateOf<CategoryId?>(null) }
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(Unit) {
            delay(300L)
            focusRequester.requestFocus()
        }

        ComposeItTextField(
            label = stringResource(id = R.string.task_add_label),
            text = taskInputText,
            onTextChange = { text -> taskInputText = text },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
        )

        CategorySelection(
            state = categoryState,
            currentCategory = currentCategory?.value,
            onCategoryChange = { categoryId -> currentCategory = categoryId },
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = {
                addTaskViewModel.addTask(taskInputText, currentCategory)
                taskInputText = ""
                onHideBottomSheet()
            },
        ) {
            Text(stringResource(id = R.string.task_add_save))
        }
    }
}

@Preview
@Composable
fun TaskListScaffoldError() {
    ComposeItTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .background(MaterialTheme.colorScheme.background),
        ) {
            AddTaskBottomSheet(onHideBottomSheet = {})
        }
    }
}
