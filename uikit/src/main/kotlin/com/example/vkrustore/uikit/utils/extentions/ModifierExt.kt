package com.example.vkrustore.uikit.utils.extentions

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.vkrustore.uikit.boxShape

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: @Composable (Modifier.() -> Modifier)?,
    ifFalse: @Composable (Modifier.() -> Modifier)?,
): Modifier = composed {
    if (condition) {
        if (ifTrue != null) then(ifTrue(Modifier)) else this
    } else {
        if (ifFalse != null) then(ifFalse(Modifier)) else this
    }
}

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: @Composable (Modifier.() -> Modifier)? = null,
): Modifier = this.conditional(condition, ifTrue, null)


fun Modifier.hideKeyboardListener(): Modifier = composed {
    val focusManager = LocalFocusManager.current

    this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }
}

fun View.isKeyboardOpen(): Boolean {
    val rect = Rect()
    getWindowVisibleDisplayFrame(rect)
    val screenHeight = rootView.height
    val keypadHeight = screenHeight - rect.bottom
    return keypadHeight > screenHeight * KEYBOARD_OPENING_THRESHOLD
}

private const val KEYBOARD_OPENING_THRESHOLD = 0.15

@Composable
fun rememberIsKeyboardOpen(): State<Boolean> {
    val view = LocalView.current

    return produceState(initialValue = view.isKeyboardOpen()) {
        val viewTreeObserver = view.viewTreeObserver
        val listener = ViewTreeObserver.OnGlobalLayoutListener { value = view.isKeyboardOpen() }
        viewTreeObserver.addOnGlobalLayoutListener(listener)

        awaitDispose { viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }
}

fun Modifier.box(
    color: Color,
    shapeSize: Dp = boxShape,
): Modifier {
    return this
        .clip(RoundedCornerShape(shapeSize))
        .background(color)
}

fun Modifier.box(
    brush: Brush,
    shapeSize: Dp = boxShape,
): Modifier {
    return this
        .clip(RoundedCornerShape(shapeSize))
        .background(brush)
}


fun Modifier.noRippleEffectClickable(
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null
): Modifier {
    return if (onClick != null) {
        this.clickable(
            enabled = enabled,
            interactionSource = MutableInteractionSource(),
            indication = null,
            onClick = onClick
        )
    } else {
        this
    }
}

fun Modifier.asyncClickable(
    enabled: Boolean,
    onClick: () -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    val latest by rememberUpdatedState(onClick)
    var running by remember { mutableStateOf(false) }

    Modifier.clickable(
        enabled = enabled && !running,
        onClick = {
            if (running) return@clickable
            running = true
            scope.launch {
                try {
                    latest()
                } finally {
                    running = false
                }
            }
        }
    )
}

fun Modifier.changeBackgroundClickable(
    defaultBackground: Color,
    pressedBackground: Color,
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier = composed {
    var isPressed by remember {
        mutableStateOf(false)
    }
    this
        .background(pressedBackground.takeIf { isPressed } ?: defaultBackground)
        .conditional(enabled) {
            pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                    onTap = {
                        onClick()
                    }
                )
            }
        }
}



fun Modifier.placeWithAddedWidth(
    addedWidth: Dp,
): Modifier {
    return layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints.copy(maxWidth = constraints.maxWidth + addedWidth.roundToPx())
        )
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}


@Composable
fun Modifier.scaledHeight(height: Dp): Modifier = composed {
    val configuration = LocalConfiguration.current
    val fontScale = configuration.fontScale
    val scaledHeight = remember(height, fontScale) {
        (height.value * fontScale).dp
    }
    this.then(Modifier.height(scaledHeight))
}

@Composable
fun Modifier.scaledWidth(width: Dp): Modifier = composed {
    val configuration = LocalConfiguration.current
    val fontScale = configuration.fontScale
    val scaledWidth = remember(width, fontScale) {
        (width.value * fontScale).dp
    }
    this.then(Modifier.width(scaledWidth))
}

@Composable
fun Modifier.scaledSize(width: Dp, height: Dp): Modifier = composed {
    val configuration = LocalConfiguration.current
    val fontScale = configuration.fontScale
    val scaledHeight = remember(height, fontScale) {
        (height.value * fontScale).dp
    }

    val scaledWidth = remember(width, fontScale) {
        (width.value * fontScale).dp
    }
    this.then(Modifier.size(scaledWidth, scaledHeight))
}

@Composable
fun Modifier.scaledSize(size: Dp): Modifier = composed {
    val configuration = LocalConfiguration.current
    val fontScale = configuration.fontScale
    val scaledSize = remember(size, fontScale) {
        (size.value * fontScale).dp
    }

    this.then(Modifier.size(scaledSize))
}

