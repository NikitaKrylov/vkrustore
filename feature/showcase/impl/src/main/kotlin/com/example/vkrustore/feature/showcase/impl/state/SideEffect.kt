package com.example.vkrustore.feature.showcase.impl.state

internal sealed interface SideEffect {
    data class NavigateToAppDetail(
        val appId: String,
    ): SideEffect

}