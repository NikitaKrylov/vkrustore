package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.feature.showcase.impl.state.MainShowcaseState
import com.example.vkrustore.feature.showcase.impl.state.SearchState
import com.example.vkrustore.feature.showcase.impl.state.ShowcaseState
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.components.ErrorBox
import com.example.vkrustore.uikit.components.ExpandedAppCard
import com.example.vkrustore.uikit.components.HorizontalAppCard
import com.example.vkrustore.uikit.components.TopSearchBar
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing2
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Showcase(
    state: MainShowcaseState,
//    onAction: (ShowcaseAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val listState = rememberLazyListState()
    val searchState = state.searchState
    val showcaseState = state.showcaseState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        TopAppBar(
            windowInsets = WindowInsets(0),
            scrollBehavior = scrollBehavior,
            title = {
                TopSearchBar(
                    modifier = Modifier
                        .padding(end = spacing16, bottom = spacing8),
                    query = searchState.query,
                    onSearch = {},
                    onQueryChange = {},
                    onLeadingClick = {},
                    onTrailingClick = {}
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            )
        )

        when (showcaseState) {
            ShowcaseState.Loading -> {
            }

            is ShowcaseState.Error ->
                ErrorBox(
                    title = showcaseState.message
                )

            is ShowcaseState.Show ->
                ShowcaseContent(
                    listState = listState,
                    blocks = showcaseState.blocks,
                    isRefreshing = showcaseState.isRefreshing
                )
        }
    }
}

@Composable
internal fun ShowcaseContent(
    listState: LazyListState,
    blocks: List<ShowcaseBlock>,
    isRefreshing: Boolean = false
) {
    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        state = rememberPullToRefreshState(),
        isRefreshing = isRefreshing,
        onRefresh = { }
    ) {
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(spacing8),
            state = listState
        ) {
            items(
                items = blocks
            ) { block ->
                when (block) {
                    is ShowcaseBlock.ExpandedApp ->
                        ExpandedAppCard(
                            modifier = Modifier
                                .padding(horizontal = spacing16)
                                .padding(bottom = spacing8),
                            bannerHead = block.head,
                            bannerSubhead = block.subhead,
                            title = block.title,
                            description = block.description,
                            rating = "4,5",
                            appAction = "action type",
                            imageUrl = ""
                        )

                    is ShowcaseBlock.AppsGroup ->
                        VerticalAppsGroup(
                            title = block.title,
                            subtitle = block.subtitle,
                            groupApp = block.apps
                        )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalAppsGroup(
    title: String,
    groupApp: List<AppPreview>,
    modifier: Modifier = Modifier,
    subtitle: String? = null
) {
    val pages = remember(groupApp) {
        groupApp.chunked(3)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = spacing16)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing2)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyles.TitleMedium
                )

                subtitle?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyles.LabelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        HorizontalAppsPager(
            pages = pages,
        )
    }
}


@Composable
fun HorizontalAppsPager(
    pages: List<List<AppPreview>>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { pages.size }

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = true,
        pageSpacing = spacing8,
        contentPadding = PaddingValues(horizontal = spacing16, vertical = spacing8),
        key = { it },
        modifier = modifier
            .fillMaxWidth()
    ) { index ->
        val items = pages[index]

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing12),
        ) {
            items.forEach { item ->
                HorizontalAppCard(
                    title = item.title,
                    description = item.description,
                    rating = "4,5",
                    actionType = "TODO()",
                    onClick = { },
                    imageUrl = ""
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ShowcasePreview() {
    val blocks = List(4) { id ->
        if (id % 2 == 0) {
            ShowcaseBlock.ExpandedApp(
                id = id.toLong(),
                title = "Title app",
                description = "best app",
                head = "Head banner",
                subhead = "Subhead banner",
                imageUrl = ""
            )
        } else {
            ShowcaseBlock.AppsGroup(
                title = "Group: $id",
                subtitle = "Sub title",
                apps = List(9) { id ->
                    AppPreview(
                        id = id.toLong(),
                        title = "Title app",
                        description = "best app",
                        imageUrl = ""
                    )
                }
            )
        }
    }

    VKRuStoreTheme {
        Showcase(
            state = MainShowcaseState(
                searchState = SearchState(""),
                showcaseState = ShowcaseState.Show(blocks = blocks)
            )
        )
    }
}
