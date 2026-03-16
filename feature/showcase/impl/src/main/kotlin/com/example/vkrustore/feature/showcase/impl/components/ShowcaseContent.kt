package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.uikit.components.ExpandedAppCard
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing8

@Composable
internal fun ShowcaseContent(
    listState: LazyListState,
    blocks: List<ShowcaseBlock>,
    isRefreshing: Boolean = false,
    onItemClick: (String) -> Unit,
) {
    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        state = rememberPullToRefreshState(),
        isRefreshing = isRefreshing,
        onRefresh = { }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
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
                            rating = block.rating?.toString(),
                            appAction = "Скачать",
                            bannerImageUrl = block.bannerImageUrl,
                            appImageUrl = block.appImageUrl,
                            onItemClick = {
                                onItemClick(block.id)
                            }
                        )

                    is ShowcaseBlock.AppsGroup ->
                        VerticalAppsGroup(
                            title = block.title,
                            subtitle = block.subtitle,
                            groupApp = block.apps,
                            onItemClick = onItemClick,
                        )
                }
            }
        }
    }
}