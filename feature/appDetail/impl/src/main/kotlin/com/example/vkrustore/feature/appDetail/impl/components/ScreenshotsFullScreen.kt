package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
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
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                pageSpacing = spacing48
            ) { page ->
                ZoomableImage(
                    imageUrl = screenshotsUrl[page]
                )
            }

            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "back"
                )
            }

            LazyRow(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = spacing32),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                state = thumbnailsListState
            ) {
                itemsIndexed(screenshotsUrl) { index, url ->
                    val isSelected = pagerState.currentPage == index

                    Box(
                        modifier = Modifier
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = null,
                            modifier = Modifier
                                .height(thumbnailHeight)
                                .aspectRatio(aspectRatio)
                                .clip(RoundedCornerShape(extraSmallShape))
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ZoomableImage(
    imageUrl: String
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val minScale = 1f
    val maxScale = 4f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        if (scale > 1f) {
                            scale = 1f
                            offset = Offset.Zero
                        } else {
                            scale = 2f
                        }
                    }
                )
            }
            .then(
                Modifier.pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        offset += dragAmount
                        change.consume()
                    }
                }.takeIf { scale > 1f } ?: Modifier
            )
            .then(
                Modifier.pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { centroid, pan, zoom, rotation ->
                            scale = (scale * zoom).coerceIn(minScale, maxScale)
                            if (scale <= 1f) offset = Offset.Zero
                        }
                    )
                }.takeIf { scale > 1f } ?: Modifier
            )
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                },
            contentScale = ContentScale.Fit
        )
    }
}