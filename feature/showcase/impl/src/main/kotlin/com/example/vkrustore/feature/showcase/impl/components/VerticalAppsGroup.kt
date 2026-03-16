package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.components.HorizontalAppCard
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing2
import com.example.vkrustore.uikit.spacing8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalAppsGroup(
    title: String,
    groupApp: List<AppPreview>,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onItemClick: (String) -> Unit,
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
            onItemClick = onItemClick,
        )
    }
}

@Composable
private fun HorizontalAppsPager(
    pages: List<List<AppPreview>>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
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
        val apps = pages[index]

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing12),
        ) {
            apps.forEach { item ->
                HorizontalAppCard(
                    title = item.title,
                    description = item.description,
                    rating = item.rating?.toString(),
                    actionType = "Скачать",
                    onClick = {
                        onItemClick(item.id)
                    },
                    imageUrl = item.imageUrl
                )
            }
        }
    }
}