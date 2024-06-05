package com.composeit.glance.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.CheckBox
import androidx.glance.appwidget.CheckboxDefaults
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.composeit.glance.R
import com.composeit.glance.data.TaskListStateDefinition
import com.composeit.glance.model.Task
import com.composeit.navigation.DestinationDeepLink

internal class TaskListGlanceWidget: GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<List<Task>> = TaskListStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) = provideContent {
        val taskList = currentState<List<Task>>()

        val background = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            GlanceModifier.cornerRadius(10.dp).background(GlanceTheme.colors.background)
        } else {
            GlanceModifier.background(ImageProvider(R.drawable.rounded_corners))
        }

        ComposeItGlanceTheme {
            androidx.glance.layout.Column(
                modifier = GlanceModifier.appWidgetBackground().fillMaxSize().then(background).padding(8.dp,)
            ) {
                Row(
                    modifier = GlanceModifier.fillMaxWidth().height(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.ic_composeit),
                        contentDescription = context.getString(R.string.glance_app_icon_content_description),
                        modifier = GlanceModifier.size(32.dp)
                            .clickable(actionStartActivity(getHomeIntent()))
                    )
                    Spacer(modifier = GlanceModifier.width(12.dp))
                    Text(
                        text = context.getString(R.string.glance_heading),
                        style = TextStyle(
                            color = GlanceTheme.colors.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                            ),
                        modifier = GlanceModifier.defaultWeight()
                        )
                }
                if (taskList.isEmpty()){
                    EmptyListContent()
                } else {
                    TaskListContent(taskList)
                }
            }
        }
    }

    @Composable
    private fun EmptyListContent(
        modifier: GlanceModifier = GlanceModifier,
        context: Context = LocalContext.current,
    ) {
        androidx.glance.layout.Column(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_task_list),
                contentDescription = context.getString(R.string.glance_no_tasks_content_description),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.secondary),
                modifier = GlanceModifier.size(48.dp),
            )
            Spacer(modifier = GlanceModifier.height(12.dp))
            Text(
                text = context.getString(R.string.glance_no_tasks),
                style = TextStyle(
                    color = GlanceTheme.colors.onSurface,
                    fontSize = 12.sp,
                ),
            )
        }
    }


    @Composable
    private fun TaskListContent(
        taskList: List<Task>,
        modifier: GlanceModifier = GlanceModifier,
    ) {
        LazyColumn(
            modifier = modifier.padding(top = 8.dp),
        ) {
            items(items = taskList) { task ->
                TaskItem(task = task)
            }
        }
    }

    @Composable
    private fun TaskItem(task: Task, modifier: GlanceModifier = GlanceModifier) {
        Row(
            modifier = modifier.padding(vertical = 2.dp).fillMaxWidth()
                .clickable(actionStartActivity(getTaskIntent(task.id))),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CheckBox(
                checked = false,
                onCheckedChange = actionRunCallback<UpdateTaskStatusAction>(
                    actionParametersOf(TaskIdKey to task.id.toString()),
                ),
                modifier = GlanceModifier.size(32.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = GlanceTheme.colors.secondary,
                    uncheckedColor = GlanceTheme.colors.onSurface,
                ),
            )
            Spacer(modifier = GlanceModifier.width(2.dp))
            Text(
                text = task.title,
                style = TextStyle(color = GlanceTheme.colors.onSurface, fontSize = 14.sp),
                maxLines = 1,
                modifier = GlanceModifier.fillMaxWidth().height(24.dp),
            )
        }
    }


    private fun getHomeIntent(): Intent =
        Intent(Intent.ACTION_VIEW,DestinationDeepLink.getTaskHomeUri())

    private fun getTaskIntent(taskId: Long): Intent =
        Intent(Intent.ACTION_VIEW, DestinationDeepLink.getTaskDetailUri(taskId))

}