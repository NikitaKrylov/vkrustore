package com.example.vkrustore.feature.appDetail.impl.state

import androidx.compose.ui.graphics.Color
import android.graphics.drawable.Drawable
import com.example.vkrustore.feature.common.state.BaseAction

internal sealed interface Actions : BaseAction {
    data object NavigateBack : Actions
    data object Submit : Actions
    data class CalcDominantColor(
        val drawable: Drawable
    ) : Actions
}