package com.example.vkrustore.feature.appDetail.impl.state

import androidx.compose.ui.graphics.Color

internal sealed interface UiState {
    data object Initialize : UiState
    data class ShowApp(
        val status: AppStatus = AppStatus.Default,
        val name: String,
        val description: String,
        val appIconUrl: String,
        val category: String,
        val screenshots: List<String>,
        val devName: String,
        val ratingAge: Int,
        val apkSize: String,
        val installCount: String,
        val ratingCount: Int,
        val rating: Float,
        val dominantColor: Color? = null
    ) : UiState
}

internal sealed interface AppStatus {
    data object Default : AppStatus
    data object Downloading : AppStatus

    data object Installing : AppStatus

    data object Installed : AppStatus
}