package com.example.vkrustore.feature.appDetail.impl.state


internal sealed interface SideEffect {
    data object NavigateBack : SideEffect
    data class ShowToast(
        val message: String
    ) : SideEffect
}