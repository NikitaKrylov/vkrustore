package com.example.vkrustore.uikit.snackbar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * Контроллер для использования вне Composable скоупа
 */
class SnackbarController {
    val snackbarList = MutableStateFlow<CustomData?>(null)
    fun showSnackbar(
        isServiceError: Boolean = false,
        message: String? = null,
        style: SnackbarStyle? = null
    ) {
        snackbarList.update {
            CustomData(
                isServiceError = isServiceError,
                message = message,
                style = style
            )
        }
    }

    fun clear() {
        snackbarList.update { null }
    }

    data class CustomData(
        val isServiceError: Boolean,
        val message: String? = null,
        val style: SnackbarStyle? = null
    )
}
