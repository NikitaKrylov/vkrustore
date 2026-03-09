package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.TopBoxShape
import com.example.vkrustore.uikit.components.ExpandableText
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing24
import com.example.vkrustore.uikit.spacing4
import com.example.vkrustore.uikit.utils.extentions.ignoreHorizontalParentPadding
import kotlinx.coroutines.launch

@Composable
fun AppInfo(
    rating: Float,
    ratingCount: Int,
    installCount: String,
    apkSize: String,
    ratingAge: Int,
    description: String,
    devName: String,
    screenshots: List<String>,
    modifier: Modifier = Modifier
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
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = TopBoxShape
            )
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing24),
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

            ExpandableText(
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