package com.composeit.category.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.amazinghorsess.category_api.model.Category
import com.amazinghorsess.category_api.presentation.CategoryListViewModel
import com.amazinghorsess.category_api.presentation.CategoryState
import com.composeit.category.R
import com.composeit.design.ComposeItTheme
import com.composeit.design.components.ComposeItFloatingButton
import com.composeit.design.components.ComposeItLoadingContent
import com.composeit.design.components.DefaultIconTextContent
import org.koin.androidx.compose.koinViewModel
import kotlin.math.max
import kotlin.math.roundToInt


@Composable
fun CategoryListSection(
    modifier: Modifier = Modifier,
    onShowBottomSheet: (Long?) -> Unit,
) {
    CategoryListLoader(
        modifier = modifier,
        onItemClick = onShowBottomSheet,
        onAddClick = { onShowBottomSheet(null) },
    )
}

@Composable
private fun CategoryListLoader(
    onItemClick: (Long?) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoryListViewModel = koinViewModel(),
) {
    val viewState by remember(viewModel) {
        viewModel.loadCategories()
    }.collectAsState(initial = CategoryState.Loading)

    CategoryListScaffold(
        modifier = modifier,
        viewState = viewState,
        onItemClick = onItemClick,
        onAddClick = onAddClick,
    )
}

@Composable
private fun CategoryListScaffold(
    viewState: CategoryState,
    onItemClick: (Long?) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints {
        val fabPosition = if (this.maxHeight > maxWidth) FabPosition.Center else FabPosition.End
        Scaffold(
            modifier = modifier.fillMaxSize(),
            floatingActionButton = {

                ComposeItFloatingButton(
                    contentDescription = R.string.category_cd_add_category,
                    onClick = { onAddClick() },
                )
            },
            floatingActionButtonPosition = fabPosition,
        ) { padding ->
            Crossfade(viewState) { state ->
                when (state) {
                    CategoryState.Loading -> ComposeItLoadingContent(modifier = Modifier.padding(padding))
                    CategoryState.Empty -> CategoryListEmpty(modifier = Modifier.padding(padding))
                    is CategoryState.Loaded -> CategoryListContent(
                        categoryList = state.categoryList,
                        onItemClick = onItemClick,
                        modifier = Modifier.padding(padding),
                    )
                }
            }
        }
    }
}

@Composable
@Suppress("MagicNumber")
private fun CategoryListContent(
    categoryList: List<Category>,
    onItemClick: (Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.padding(start = 8.dp, end = 8.dp)) {
        val cellCount: Int = max(2F, maxWidth.value / 250).roundToInt()
        LazyVerticalGrid(columns = GridCells.Fixed(cellCount)) {
            items(
                items = categoryList,
                itemContent = { category ->
                    CategoryItem(category = category, onItemClick = onItemClick)
                },
            )
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category,
    onItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable { onItemClick(category.id) },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
        ) {
            CategoryItemIcon(category.color)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = category.name)
        }
    }
}

@Composable
private fun CategoryItemIcon(color: Int) {
    Box(contentAlignment = Alignment.Center) {
        CategoryCircleIndicator(size = 48.dp, color = color, alpha = 0.2F)
        CategoryCircleIndicator(size = 40.dp, color = color)
        Icon(
            imageVector = Icons.Default.Bookmark,
            contentDescription = stringResource(id = R.string.category_icon_cd),
            tint = MaterialTheme.colorScheme.background,
        )
    }
}

@Composable
private fun CategoryCircleIndicator(size: Dp, color: Int, alpha: Float = 1F) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .alpha(alpha)
            .background(Color(color)),
    )
}
@Composable
private fun CategoryListEmpty(modifier: Modifier = Modifier) {
    DefaultIconTextContent(
        icon = Icons.Outlined.ThumbUp,
        iconContentDescription = R.string.category_list_cd_empty_list,
        text = R.string.category_list_header_empty,
        modifier = modifier,
    )
}


@Preview
@Composable
fun AboutPreview() {
    ComposeItTheme {
        CategoryItem(
            category = Category(name = "Movies", color = android.graphics.Color.RED),
            onItemClick = { },
        )
    }
}