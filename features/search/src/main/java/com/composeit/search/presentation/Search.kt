package com.composeit.search.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composeit.design.ComposeItTheme
import com.composeit.design.components.ComposeItLoadingContent
import com.composeit.design.components.DefaultIconTextContent
import com.composeit.search.R
import com.composeit.search.model.TaskSearchItem
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchSection(modifier: Modifier = Modifier, onItemClick: (Long) -> Unit) {
    SearchLoader(modifier = modifier, onItemClick = onItemClick)
}

@Composable
private fun SearchLoader(
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = getViewModel(),
) {
    val (query, setQuery) = rememberSaveable { mutableStateOf("") }
    val viewState by remember(viewModel, query) {
        viewModel.findTaskByName(query)
    }.collectAsState(initial = SearchViewState.Loading)

    SearchScaffold(
        viewState = viewState,
        modifier = modifier,
        onItemClick = onItemClick,
        query = query,
        setQuery = setQuery,
    )
}

@Composable
internal fun SearchScaffold(
    viewState: SearchViewState,
    onItemClick: (Long) -> Unit,
    query: String,
    setQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
        ) {
            SearchTextField(query, onTextChange = setQuery)

            Crossfade(viewState) { state ->
                when (state) {
                    SearchViewState.Loading -> ComposeItLoadingContent()
                    SearchViewState.Empty -> SearchEmptyContent()
                    is SearchViewState.Loaded -> SearchListContent(
                        taskList = state.taskList,
                        onItemClick = onItemClick,
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchTextField(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_cd_icon),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    )
}

@Composable
private fun SearchEmptyContent() {
    DefaultIconTextContent(
        icon = Icons.Outlined.ExitToApp,
        iconContentDescription = R.string.search_cd_empty_list,
        text = R.string.search_header_empty,
    )
}

@Composable
private fun SearchListContent(
    taskList: List<TaskSearchItem>,
    onItemClick: (Long) -> Unit,
) {
    LazyColumn {
        items(
            items = taskList,
            itemContent = { task -> SearchItem(task = task, onItemClick = onItemClick) },
        )
    }
}

@Composable
private fun SearchItem(task: TaskSearchItem, onItemClick: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable { onItemClick(task.id) },
        verticalArrangement = Arrangement.Center,
    ) {
        val textDecoration: TextDecoration
        val circleColor: Color

        if (task.completed) {
            textDecoration = TextDecoration.LineThrough
            circleColor = MaterialTheme.colorScheme.outline
        } else {
            textDecoration = TextDecoration.None
            circleColor = task.categoryColor ?: MaterialTheme.colorScheme.surfaceVariant
        }

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(circleColor),
            )
            Text(
                text = task.title,
                textDecoration = textDecoration,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .height(24.dp),
            )
        }
    }
}


@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun SearchLoadedListPreview() {
    val task1 = TaskSearchItem(
        completed = true,
        title = "Call Me By Your Name",
        categoryColor = Color.Green,
        isRepeating = false,
    )

    val task2 = TaskSearchItem(
        completed = false,
        title = "The Crown",
        categoryColor = Color.White,
        isRepeating = true,
    )

    val taskList = listOf(task1, task2)

    ComposeItTheme {
        SearchScaffold(
            modifier = Modifier,
            viewState = SearchViewState.Loaded(taskList),
            onItemClick = {},
            query = "",
            setQuery = {},
        )
    }
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun SearchEmptyListPreview() {
    ComposeItTheme {
        SearchScaffold(
            modifier = Modifier,
            viewState = SearchViewState.Empty,
            onItemClick = {},
            query = "",
            setQuery = {},
        )
    }
}

