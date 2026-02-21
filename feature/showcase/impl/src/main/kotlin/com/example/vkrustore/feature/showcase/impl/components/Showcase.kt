package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.components.ExpandedAppCard
import com.example.vkrustore.uikit.components.HorizontalAppCard
import com.example.vkrustore.uikit.mediumShape
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing2
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.theme.VKRuStoreTheme
import kotlin.collections.buildList
import kotlin.collections.chunked

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Showcase(
    blocks: List<ShowcaseBlock>
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val listState = rememberLazyListState()

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
                        .padding(end = spacing16, bottom = spacing8)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            )
        )

        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(spacing8),
            state = listState
        ) {
            items(
                items = blocks,
                key = { it.id }
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
                            containerColor = Color.Cyan,
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
fun TopSearchBar(
    modifier: Modifier = Modifier
) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState("")

    SearchBar(
        modifier = modifier
            .fillMaxWidth(),
        state = searchBarState,
        tonalElevation = 0.dp,
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                onQueryChange = {
                    textFieldState.edit { replace(0, length, it) }
                },
                onSearch = {},
                expanded = true,
                onExpandedChange = {},
                placeholder = {
                    Text(
                        text = "Поиск в RuStore",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = TextStyles.LabelLarge
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_search_24),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = "search"
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_mic_none_24),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = "voice search"
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
        },
        shape = RoundedCornerShape(mediumShape),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
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
                    painter = painterResource(R.drawable.love_vk)
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
                subhead = "Subhead banner"
            )
        } else {
            ShowcaseBlock.AppsGroup(
                id = id.toLong(),
                title = "Group: $id",
                subtitle = "Sub title",
                apps = List(9) { id ->
                    AppPreview(
                        id = id.toLong(),
                        title = "Title app",
                        description = "best app"
                    )
                }
            )
        }
    }

    VKRuStoreTheme {
        Showcase(
            blocks = blocks
        )
    }
}
