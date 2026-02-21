package com.example.vkrustore.feature.showcase.impl.state

import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock

sealed interface ShowcaseState {
    data object Loading : ShowcaseState
    data class Error(
        val message: String
    ) : ShowcaseState

    data class Show(
        val blocks: List<ShowcaseBlock> = emptyList(),
        val isRefreshing: Boolean = false
    ) : ShowcaseState
}
