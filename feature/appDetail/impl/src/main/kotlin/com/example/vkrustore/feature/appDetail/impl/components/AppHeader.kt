package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.toBitmap
import com.example.vkrustore.feature.appDetail.impl.state.Actions
import com.example.vkrustore.feature.appDetail.impl.state.AppStatus
import com.example.vkrustore.uikit.BottomBoxShape
import com.example.vkrustore.uikit.IconAppShape
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.buttonPaddingValues
import com.example.vkrustore.uikit.components.AppAsyncImage
import com.example.vkrustore.uikit.components.PrimaryButton
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing24
import com.example.vkrustore.uikit.spacing4

@Composable
internal fun AppHeader(
    appIconUrl: String,
    appName: String,
    category: String,
    status: AppStatus,
    dominantColor: Color,
    modifier: Modifier = Modifier,
    onAction: (Actions) -> Unit
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    val animatedColor by animateColorAsState(
        dominantColor,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
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
                        colors = listOf(animatedColor.copy(alpha = 0.2f), defaultDominantColor)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(spacing24),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppAsyncImage(
                modifier = Modifier
                    .size(84.dp),
                model = appIconUrl,
                contentDescription = "app card image",
                onSuccess = {
                    val img = it.result.image.toBitmap()
                    onAction(Actions.CalcDominantColor(img))
                },
                shape = IconAppShape
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