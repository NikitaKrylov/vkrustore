package com.example.vkrustore.feature.appDetail.impl.state

internal sealed interface UiState {
    data object Initialize : UiState
    data class ShowApp(
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
    ) : UiState
}