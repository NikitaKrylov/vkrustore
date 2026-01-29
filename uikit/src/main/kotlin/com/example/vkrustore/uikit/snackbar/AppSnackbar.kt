package com.example.vkrustore.uikit.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AccessibilityManager
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vkrustore.uikit.snackbarShape
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing8
import com.example.vkrustore.uikit.utils.extentions.box
import kotlinx.coroutines.delay

@Composable
fun AppSnackbar(
    state: AppSnackbarState
) {
    val snackbarItems by remember { derivedStateOf { state.currentSnackbarData.toList() } }
    LazyColumn(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 56.dp),
        userScrollEnabled = false
    ) {
        items(snackbarItems, key = { it.visuals.message }) { snackbar ->
            AppSnackbar(snackbar)
        }
    }
}

@Composable
private fun AppSnackbar(
    currentSnackbarData: SnackbarData,
) {
    val accessibilityManager = LocalAccessibilityManager.current
    val draggableState = rememberDraggableState { delta ->
        if (delta > 0) {
            currentSnackbarData.visuals.onHandleDismiss()
            currentSnackbarData.dismiss()
        }
    }
    var isItemVisible by remember { mutableStateOf(false) }

    AnimatedVisibility(
        modifier = Modifier
            .padding(top = spacing12)
            .draggable(
                draggableState,
                Orientation.Horizontal
            ),
        visible = isItemVisible,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        CustomSnackbar(currentSnackbarData)
    }

    // Для отработки анимации появления
    LaunchedEffect(Unit) {
        isItemVisible = true
    }

    LaunchedEffect(currentSnackbarData) {
        val duration = currentSnackbarData.visuals.duration.toMillis(
            currentSnackbarData.visuals.actionLabel != null,
            accessibilityManager
        )
        delay(duration)
        isItemVisible = false
        delay(300) // Чтобы успела отработать анимация исчезновения
        currentSnackbarData.dismiss()
    }
}

@Composable
private fun CustomSnackbar(
    currentData: SnackbarData?,
) {
    var state: SnackbarData? by remember { mutableStateOf(null) }
    if (currentData != null) {
        state = currentData

        state?.let { data ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .box(
                        color = data.visuals.style.backgroundColor,
                        shapeSize = snackbarShape,
                    )
                    .padding(spacing16),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                data.visuals.iconRes?.let { iconRes ->
                    Icon(
                        modifier = Modifier.padding(end = spacing8),
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        tint = data.visuals.style.contentColor,
                    )
                }

                Text(
                    modifier = Modifier.weight(1f, fill = false),
                    text = data.visuals.message,
                    textAlign = TextAlign.Center.takeIf { data.visuals.iconRes == null }
                        ?: TextAlign.Start,
                )
            }
        }
    }
}

private fun SnackbarDuration.toMillis(
    hasAction: Boolean,
    accessibilityManager: AccessibilityManager?,
): Long {
    val original = when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> LONG_SNACKBAR_DURATION
        SnackbarDuration.Short -> SHORT_SNACKBAR_DURATION
    }
    if (accessibilityManager == null) {
        return original
    }
    return accessibilityManager.calculateRecommendedTimeoutMillis(
        original,
        containsIcons = true,
        containsText = true,
        containsControls = hasAction
    )
}

private const val LONG_SNACKBAR_DURATION = 10000L
private const val SHORT_SNACKBAR_DURATION = 4000L
