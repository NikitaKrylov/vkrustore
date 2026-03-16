package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.extraSmallShape
import com.example.vkrustore.uikit.spacing32
import com.example.vkrustore.uikit.spacing48
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun ScreenshotsFullScreen(
    screenshotsUrl: List<String>,
    startIndex: Int,
    onDismiss: () -> Unit,
    aspectRatio: Float
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { screenshotsUrl.size }
    )
    val thumbnailsListState = rememberLazyListState()
    val thumbnailHeight = 68.dp

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page ->
                val layoutInfo = thumbnailsListState.layoutInfo
                val visibleItem = layoutInfo.visibleItemsInfo
                    .firstOrNull { it.index == page }

                if (visibleItem != null) {
                    val viewportCenter = layoutInfo.viewportEndOffset / 2
                    val itemCenter = visibleItem.offset + visibleItem.size / 2
                    val scrollOffset = itemCenter - viewportCenter

                    thumbnailsListState.animateScrollBy(scrollOffset.toFloat())
                } else {
                    thumbnailsListState.animateScrollToItem(page)
                }
            }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .background(Color.Black)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                }

                HorizontalPager(
                    modifier = Modifier
                        .weight(1f)
                        .clipToBounds(),
                    state = pagerState,
                    pageSpacing = spacing48
                ) { page ->
                    ZoomableImage(
                        imageUrl = screenshotsUrl[page]
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    state = thumbnailsListState
                ) {
                    itemsIndexed(screenshotsUrl) { index, url ->
                        val isSelected = pagerState.currentPage == index

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(extraSmallShape))
                        ) {
                            AsyncImage(
                                model = url,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(thumbnailHeight)
                                    .aspectRatio(aspectRatio)
                                    .clickable {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                contentScale = ContentScale.Crop
                            )

                            if (!isSelected) {
                                Box(
                                    Modifier
                                        .matchParentSize()
                                        .background(Color.Black.copy(alpha = 0.5f))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ZoomableImage(
    imageUrl: String
) {
    val zoomState = rememberZoomState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .zoomable(
                    zoomState = zoomState,
                    onDoubleTap = { position ->
                        val targetScale = 1f.takeIf { zoomState.scale > 1f } ?: 2f
                        zoomState.changeScale(targetScale, position)
                    }
                ),
            contentScale = ContentScale.Fit,
            onSuccess = { state ->
                zoomState.setContentSize(state.painter.intrinsicSize)
            }
        )
    }
}
