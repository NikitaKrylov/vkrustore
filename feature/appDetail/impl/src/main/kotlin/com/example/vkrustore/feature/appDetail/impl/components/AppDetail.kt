package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.toBitmap
import com.example.vkrustore.feature.appDetail.impl.state.Actions
import com.example.vkrustore.feature.appDetail.impl.state.AppStatus
import com.example.vkrustore.feature.appDetail.impl.state.UiState
import com.example.vkrustore.uikit.BottomBoxShape
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.TopBoxShape
import com.example.vkrustore.uikit.boxShape
import com.example.vkrustore.uikit.buttonPaddingValues
import com.example.vkrustore.uikit.components.AppImageError
import com.example.vkrustore.uikit.components.ExpandableDescription
import com.example.vkrustore.uikit.components.PrimaryButton
import com.example.vkrustore.uikit.extraSmall
import com.example.vkrustore.uikit.smallShape
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing24
import com.example.vkrustore.uikit.spacing32
import com.example.vkrustore.uikit.spacing4
import com.example.vkrustore.uikit.spacing48
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.theme.VKRuStoreTheme
import com.example.vkrustore.uikit.utils.extentions.ignoreHorizontalParentPadding
import com.example.vkrustore.uikit.utils.extentions.statusBarPadding
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppDetail(
    state: UiState.ShowApp,
    onAction: (Actions) -> Unit
) {
    val scrollState = rememberScrollState()
    val alpha by remember {
        derivedStateOf {
            (scrollState.value / 400f).coerceIn(0f, 1f)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(spacing8)
    ) {
        AppHeader(
            appIconUrl = state.appIconUrl,
            appName = state.name,
            category = state.category,
            status = state.status,
            dominantColor = state.dominantColor ?: MaterialTheme.colorScheme.surfaceVariant,
            onAction = onAction
        )

        AppDetails(
            rating = state.rating,
            ratingCount = state.ratingCount,
            installCount = state.installCount,
            apkSize = state.apkSize,
            ratingAge = state.ratingAge,
            description = state.description,
            devName = state.devName,
            screenshots = state.screenshots
        )
    }

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    onAction(Actions.NavigateBack)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "add/remove from favorites"
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_more_vert_24),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.alpha(alpha),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing8)
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(42.dp),
                    model = state.appIconUrl,
                    contentDescription = "app card image",
                    contentScale = ContentScale.Crop,
                    error = {
                        AppImageError(
                            shape = RoundedCornerShape(smallShape)
                        )
                    }
                )

                Text(
                    text = state.name,
                    style = TextStyles.TitleSmall
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = alpha),
            scrolledContainerColor = Color.Transparent
        )
    )
}

@Composable
internal fun AppHeader(
    appIconUrl: String,
    appName: String,
    category: String,
    status: AppStatus,
    dominantColor: Color,
    onAction: (Actions) -> Unit
) {
    val localResource = LocalResources.current
    val defaultDominantColor = MaterialTheme.colorScheme.surfaceVariant
    val animatedColor by animateColorAsState(
        dominantColor,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BottomBoxShape
            )
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(animatedColor.copy(alpha = 0.25f), defaultDominantColor)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarPadding()
                .padding(spacing24),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(84.dp),
                model = appIconUrl,
                contentDescription = "app card image",
                contentScale = ContentScale.Crop,
                onSuccess = {
                    val img = it.result.image.toBitmap()
                    onAction(Actions.CalcDominantColor(img))
                },
                error = {
                    AppImageError(
                        shape = RoundedCornerShape(boxShape)
                    )
                }
            )

            Spacer(Modifier.height(spacing16))

            Text(
                text = appName,
                style = TextStyles.TitleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(spacing4))

            Text(
                text = category,
                style = TextStyles.LabelMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(spacing12))

            PrimaryButton(
                onClick = {
                    onAction(Actions.Submit)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = buttonPaddingValues
            ) {
                val text = when (status) {
                    AppStatus.Default -> "Установить"
                    AppStatus.Downloading -> "Скачивание..."
                    AppStatus.Installed -> "Удалить"
                    AppStatus.Installing -> "Установка..."
                }
                Text(
                    text = text,
                    style = TextStyles.TitleSmall
                )
            }
        }
    }
}

@Composable
internal fun AppDetails(
    rating: Float,
    ratingCount: Int,
    installCount: String,
    apkSize: String,
    ratingAge: Int,
    description: String,
    devName: String,
    screenshots: List<String>
) {
    val coroutineScope = rememberCoroutineScope()
    var fullScreenIndex by remember { mutableStateOf<Int?>(null) }
    var aspectRatio by remember { mutableFloatStateOf(1f) }
    val image = rememberAsyncImagePainter(model = screenshots.firstOrNull())

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            image.state.collect {
                if (it is AsyncImagePainter.State.Success) {
                    val img = it.result.image

                    aspectRatio = if (img.width > img.height) {
                        16f / 9f
                    } else 9f / 16f
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = TopBoxShape
            )
            .fillMaxSize()
            .padding(spacing24)
            .clipToBounds(),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(spacing16)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .ignoreHorizontalParentPadding(spacing24)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(spacing12)
            ) {
                InfoColumnItem(
                    title = rating.toString(),
                    subtitle = "$ratingCount оценок",
                    painter = painterResource(R.drawable.baseline_star_24)
                )
                InfoColumnItem(
                    title = installCount,
                    subtitle = "Скачиваний"
                )
                InfoColumnItem(
                    title = apkSize,
                    subtitle = "Размер"
                )
                InfoColumnItem(
                    title = ratingAge.toString(),
                    subtitle = "Возраст"
                )
            }

            ScreenshotsRow(
                modifier = Modifier
                    .ignoreHorizontalParentPadding(spacing24),
                screenshotsUrl = screenshots,
                onClick = { index ->
                    fullScreenIndex = index
                },
                aspectRatio = aspectRatio
            )

            fullScreenIndex?.let { index ->
                ScreenshotsFullScreen(
                    screenshotsUrl = screenshots,
                    startIndex = index,
                    aspectRatio = aspectRatio,
                    onDismiss = { fullScreenIndex = null }
                )
            }

            ExpandableDescription(
                text = description
            )

            HorizontalDivider()

            InfoColumnItem(
                title = devName,
                subtitle = "Разработчик"
            )
        }
    }
}

@Composable
fun ScreenshotsRow(
    screenshotsUrl: List<String>,
    onClick: (Int) -> Unit,
    aspectRatio: Float,
    modifier: Modifier = Modifier
) {
    val fixedHeight = 180.dp

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = spacing16),
        horizontalArrangement = Arrangement.spacedBy(spacing12)
    ) {
        itemsIndexed(screenshotsUrl) { index, url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .height(fixedHeight)
                    .aspectRatio(aspectRatio)
                    .clip(RoundedCornerShape(extraSmall))
                    .clickable { onClick(index) },
                contentScale = ContentScale.Crop
            )
        }
    }
}

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
                                .clip(RoundedCornerShape(extraSmall))
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
fun ZoomableImage(
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

@Composable
fun InfoColumnItem(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    verticalSpacing: Dp = spacing4
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing4)
        ) {
            val textStyle = TextStyles.TitleMedium

            painter?.let {
                val iconSize = with(LocalDensity.current) {
                    textStyle.fontSize.toDp() - 4.dp
                }

                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSize)
                )
            }

            Text(
                text = title,
                style = textStyle
            )
        }

        Text(
            text = subtitle,
            style = TextStyles.LabelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewAppDetail() {
    VKRuStoreTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AppDetail(
                state = UiState.ShowApp(
                    status = AppStatus.Downloading,
                    name = "TODO()",
                    description = "TODO()",
                    appIconUrl = "TODO()",
                    category = "TODO()",
                    screenshots = emptyList(),
                    devName = "TODO()",
                    ratingAge = 5,
                    apkSize = "sdfsdf",
                    installCount = "f",
                    ratingCount = 1,
                    rating = 1f
                ),
                onAction = { }
            )
        }
    }
}