package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
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
import com.example.vkrustore.uikit.spacing16
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
    val searchQuery = rememberTextFieldState(searchState.query)
    val isSearchEmpty by remember(searchQuery.text) {
        derivedStateOf { searchQuery.text.isBlank() }
    }
    val fraction = scrollBehavior.state.collapsedFraction
    val visibleFraction = 1f - fraction.coerceIn(0f, 1f)
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.statusBars,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(bottom = spacing8),
                windowInsets = WindowInsets.statusBars,
                scrollBehavior = scrollBehavior,
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                )
            )
        }
    ) { innerPaddings ->
        Box(Modifier.padding(innerPaddings)) {
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

    TopSearchBar(
        modifier = Modifier
            .padding(horizontal = 0.dp.takeIf { expanded } ?: spacing16)
            .graphicsLayer {
                clip = true
                scaleY = visibleFraction
                alpha = visibleFraction
                transformOrigin = TransformOrigin(0.5f, 0.4f)
            },
        expanded = expanded,
        onExpandedChange = { expanded = it },
        query = searchQuery.text.toString(),
        onQueryChange = {
            searchQuery.edit { replace(0, length, it) }
        },
        onSearch = {
            onAction(ShowcaseAction.OnSearch(it))
            expanded = false
        },
        onLeadingClick = {
            onAction(ShowcaseAction.OnSearch(searchQuery.text.toString()))
        },
        onTrailingClick = {
            if (isSearchEmpty) {
                // speech to text
            } else {
                searchQuery.edit { replace(0, length, "") }
                onAction(ShowcaseAction.OnClearSearch)
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = "search"
            )
        },
        trailingIcon = {
            if (isSearchEmpty) {
                Icon(
                    painter = painterResource(R.drawable.baseline_mic_none_24),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "voice input"
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.cancel),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "clear the input"
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
