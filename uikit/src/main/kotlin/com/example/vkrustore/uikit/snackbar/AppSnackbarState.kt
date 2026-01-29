package com.example.vkrustore.uikit.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@Stable
class AppSnackbarState {

    private val scope = CoroutineScope(Dispatchers.IO)

    var currentSnackbarData = mutableStateListOf<SnackbarData>()
        private set

    fun hideSnackbar(
        tag: String
    ) {
        currentSnackbarData.firstOrNull { it.visuals.tag == tag }?.dismiss()
    }

    fun showSnackbar(
        message: String,
        style: SnackbarStyle,
        iconRes: Int? = null,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        tag: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short.takeIf { actionLabel == null }
            ?: SnackbarDuration.Indefinite,
        onHandleDismiss: () -> Unit = {}
    ) {
        if (currentSnackbarData.find { it.visuals.message == message } != null) return

        scope.launch {
            showSnackbar(
                SnackbarVisualsImpl(
                    style,
                    iconRes,
                    message,
                    actionLabel,
                    withDismissAction,
                    tag,
                    duration,
                    onHandleDismiss
                )
            )
        }
    }

    private suspend fun showSnackbar(visuals: SnackbarVisuals): SnackbarResult {
        try {
            return suspendCancellableCoroutine { continuation ->
                currentSnackbarData.add(SnackbarDataImpl(visuals, continuation))
            }
        } finally {
            currentSnackbarData.remove(currentSnackbarData.first { it.visuals.message == visuals.message })
        }
    }

    private class SnackbarDataImpl(
        override val visuals: SnackbarVisuals,
        private val continuation: CancellableContinuation<SnackbarResult>
    ) : SnackbarData {

        override fun performAction() {
            if (continuation.isActive) continuation.resume(SnackbarResult.ActionPerformed)
        }

        override fun dismiss() {
            if (continuation.isActive) continuation.resume(SnackbarResult.Dismissed)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as SnackbarDataImpl

            if (visuals != other.visuals) return false
            return continuation == other.continuation
        }

        override fun hashCode(): Int {
            var result = visuals.hashCode()
            result = 31 * result + continuation.hashCode()
            return result
        }
    }
}

@Composable
fun rememberAppSnackbarState(): AppSnackbarState {
    return remember {
        AppSnackbarState()
    }
}
