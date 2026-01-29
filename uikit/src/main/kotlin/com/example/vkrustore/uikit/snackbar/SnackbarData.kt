package com.example.vkrustore.uikit.snackbar

import androidx.compose.runtime.Stable

@Stable
interface SnackbarData {
    val visuals: SnackbarVisuals

    fun performAction()

    fun dismiss()
}
