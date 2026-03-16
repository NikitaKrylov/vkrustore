package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.vkrustore.feature.appDetail.impl.state.Actions
import com.example.vkrustore.feature.appDetail.impl.state.AppStatus
import com.example.vkrustore.feature.appDetail.impl.state.UiState
import com.example.vkrustore.uikit.IconAppShape
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.components.AppAsyncImage
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.theme.VKRuStoreTheme


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

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth(),
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

            AppInfo(
                modifier = Modifier
                    .heightIn(min = screenHeight)
                    .fillMaxWidth(),
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
                AppAsyncImage(
                    modifier = Modifier
                        .size(42.dp),
                    model = state.appIconUrl,
                    contentDescription = "app card image",
                    shape = IconAppShape
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

@PreviewLightDark
@Composable
private fun PreviewAppDetail() {
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