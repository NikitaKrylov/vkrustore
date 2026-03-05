package com.example.vkrustore.feature.appDetail.impl.state

import android.graphics.Bitmap
import com.example.vkrustore.feature.common.state.BaseAction

internal sealed interface Actions : BaseAction {
    data object NavigateBack : Actions
    data object Submit : Actions
    data class CalcDominantColor(
        val bitmap: Bitmap
    ) : Actions
}