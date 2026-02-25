package com.example.vkrustore.feature.showcase.api

sealed interface ShowcaseAction {
    data object OnRefresh : ShowcaseAction
    data class OnAppClick(val appId: String) : ShowcaseAction
    data class OnSearch(val query: String) : ShowcaseAction
    data object OnClearSearch : ShowcaseAction
}