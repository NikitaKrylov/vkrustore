package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.ShowcaseAction
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.feature.showcase.impl.state.MainShowcaseState
import com.example.vkrustore.feature.showcase.impl.state.SearchState
import com.example.vkrustore.feature.showcase.impl.state.ShowcaseState
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.components.ErrorBox
import com.example.vkrustore.uikit.components.TopSearchBar
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Showcase(
    state: MainShowcaseState,
    onAction: (ShowcaseAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val listState = rememberLazyListState()
    val searchState = state.searchState
    val showcaseState = state.showcaseState

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = MaterialTheme.colorScheme.surface,
            contentWindowInsets = WindowInsets(0),
            topBar = {
                TopAppBar(
                    title = {},
                    windowInsets = WindowInsets.statusBars,
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent
                    )
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                when (showcaseState) {
                    ShowcaseState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(120.dp)
                            )
                        }
                    }

                    is ShowcaseState.Error ->
                        ErrorBox(
                            description = showcaseState.message,
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.geometric),
                                    contentDescription = null,
                                    modifier = Modifier.size(140.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        )

                    is ShowcaseState.Show ->
                        ShowcaseContent(
                            listState = listState,
                            blocks = showcaseState.blocks,
                            isRefreshing = showcaseState.isRefreshing,
                            onItemClick = { id ->
                                onAction(ShowcaseAction.OnAppClick(id))
                            }
                        )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .clipToBounds()
                .padding(bottom = spacing8)
                .graphicsLayer {
                    translationY = scrollBehavior.state.heightOffset
                }
        ) {
            SearchBar(
                searchState = searchState,
                onAction = onAction
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    searchState: SearchState,
    onAction: (ShowcaseAction) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember(searchState.query) {
        mutableStateOf(searchState.query)
    }
    val isSearchEmpty = query.isBlank()
    var expanded by remember { mutableStateOf(false) }

    TopSearchBar(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it },
        query = query,
        onQueryChange = {
            query = it
        },
        onSearch = {
            onAction(ShowcaseAction.OnSearch(it))
            expanded = false
        },
        leadingIcon = {
            IconButton(onClick = { onAction(ShowcaseAction.OnSearch(query)) }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_search_24),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "search"
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (isSearchEmpty) {
                        // speech to text
                    } else {
                        query = ""
                        onAction(ShowcaseAction.OnClearSearch)
                    }
                }
            ) {
                val iconRes = if (isSearchEmpty) R.drawable.baseline_mic_none_24 else R.drawable.cancel
                Icon(
                    painter = painterResource(iconRes),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = if (isSearchEmpty) "voice input" else "clear the input"
                )
            }
        }
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun ShowcasePreview() {
    val blocks = List(4) { id ->
        if (id % 2 == 0) {
            ShowcaseBlock.ExpandedApp(
                id = id.toString(),
                title = "Title app",
                description = "best app",
                head = "Head banner",
                subhead = "Subhead banner",
                bannerImageUrl = "",
                appImageUrl = "",
                rating = 5f
            )
        } else {
            ShowcaseBlock.AppsGroup(
                title = "Group: $id",
                subtitle = "Sub title",
                apps = List(9) { id ->
                    AppPreview(
                        id = id.toString(),
                        title = "Title app",
                        description = "best app",
                        rating = 5f,
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
            ),
            onAction = {}
        )
    }
}
