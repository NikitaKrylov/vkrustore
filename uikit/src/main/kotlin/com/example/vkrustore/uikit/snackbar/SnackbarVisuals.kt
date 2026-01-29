package com.example.vkrustore.uikit.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Stable

@Stable
interface SnackbarVisuals {
    val style: SnackbarStyle
    val iconRes: Int?
    val message: String
    val actionLabel: String?
    val withDismissAction: Boolean
    val tag: String?
    val duration: SnackbarDuration
    val onHandleDismiss: () -> Unit
}

class SnackbarVisualsImpl(
    override val style: SnackbarStyle,
    override val iconRes: Int?,
    override val message: String,
    override val actionLabel: String?,
    override val withDismissAction: Boolean,
    override val tag: String?,
    override val duration: SnackbarDuration,
    override val onHandleDismiss: () -> Unit
) : SnackbarVisuals {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SnackbarVisualsImpl

        if (style != other.style) return false
        if (iconRes != other.iconRes) return false
        if (message != other.message) return false
        if (actionLabel != other.actionLabel) return false
        if (withDismissAction != other.withDismissAction) return false
        if (tag != other.tag) return false
        return duration == other.duration
    }

    override fun hashCode(): Int {
        var result = style.hashCode()
        result = 31 * result + (iconRes ?: 0)
        result = 31 * result + message.hashCode()
        result = 31 * result + (actionLabel?.hashCode() ?: 0)
        result = 31 * result + withDismissAction.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + duration.hashCode()
        return result
    }
}
