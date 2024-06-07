package com.composeit.tracker.presentation

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DynamicFeed
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DataUsage
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.composeit.design.components.ComposeItLoadingContent
import com.composeit.design.components.ComposeItTopAppBar
import com.composeit.design.components.DefaultIconTextContent
import com.composeit.tracker.R
import com.composeit.tracker.model.Tracker
import com.composeit.tracker.presentation.components.TaskGraph
import com.composeit.tracker.presentation.components.TaskTrackerList
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun TrackerSection(onUpPress: () -> Unit) {
    TrackerLoader(onUpPress = onUpPress)
}

@Composable
internal fun TrackerLoader(viewModel: TrackerViewModel = koinViewModel(), onUpPress: () -> Unit) {
    val data by remember {
        viewModel.loadTracker()
    }.collectAsState(initial = TrackerViewState.Loading)

    Scaffold(topBar = { ComposeItTopAppBar(onPress = onUpPress) }) { paddingValues ->
        Crossfade(targetState = data, modifier = Modifier.padding(paddingValues)) { state ->
            Log.d("TrackerLoader", "Current state: $state")

            when (state) {
                TrackerViewState.Empty -> TrackerEmpty()
                is TrackerViewState.Error -> TrackerError()
                is TrackerViewState.Loaded -> TrackerLoadedContent(state.trackerInfo)
                TrackerViewState.Loading -> ComposeItLoadingContent()
            }
        }
    }
}

@Composable
@Suppress("MagicNumber")
private fun TrackerLoadedContent(trackerInfo: Tracker.Info) {
    val categoryList = trackerInfo.categoryInfo
    Column {
        TaskGraph(
            list = categoryList,
            modifier = Modifier
                .fillMaxWidth()
                .weight(3F)
                .padding(24.dp),
        )
        TaskTrackerList(
            list = categoryList,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2F),
        )
        TaskTrackerInfoCard(
            list = categoryList,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(24.dp),
        )
    }
}

@Composable
private fun TrackerEmpty() {
    DefaultIconTextContent(
        icon = Icons.Outlined.DataUsage,
        iconContentDescription = R.string.tracker_cd_empty,
        text = R.string.tracker_header_empty,
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
private fun TrackerError() {
    DefaultIconTextContent(
        icon = Icons.Outlined.Close,
        iconContentDescription = R.string.tracker_cd_error,
        text = R.string.tracker_header_error,
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
@Suppress("MagicNumber")
private fun TaskTrackerInfoCard(
    list: List<Tracker.CategoryInfo>,
    modifier: Modifier = Modifier,
) {
    val taskCount = list.sumOf { item -> item.taskCount }
    val message = LocalContext.current.resources
        .getQuantityString(R.plurals.tracker_message_title, taskCount, taskCount)

    ElevatedCard(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Default.DynamicFeed,
                contentDescription = stringResource(id = R.string.tracker_cp_info_icon),
                modifier = Modifier
                    .weight(1F)
                    .size(36.dp),
            )
            Column(modifier = Modifier.weight(3F)) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(id = R.string.tracker_message_description),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}