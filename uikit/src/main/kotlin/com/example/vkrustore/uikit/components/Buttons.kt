package com.example.vkrustore.uikit.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.smallShape
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.spacing32
import com.example.vkrustore.uikit.spacing40
import com.example.vkrustore.uikit.theme.VKRuStoreTheme


enum class ButtonType {
    Primary,
    Secondary
}


@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Primary,
    shape: Shape = RoundedCornerShape(smallShape),
    contentPadding: PaddingValues = PaddingValues(horizontal = spacing16),
    content: @Composable RowScope.() -> Unit
) {
    val buttonContent: @Composable (@Composable RowScope.() -> Unit) -> Unit = when (buttonType) {
        ButtonType.Primary -> { content ->
            Button(
                onClick = onClick,
                shape = shape,
                contentPadding = contentPadding,
                modifier = modifier,
                content = content
            )
        }

        ButtonType.Secondary -> { content ->
            FilledTonalButton(
                onClick = onClick,
                shape = shape,
                contentPadding = contentPadding,
                modifier = modifier,
                content = content
            )
        }
    }

    buttonContent { content() }
}


@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(smallShape),
    contentPadding: PaddingValues = PaddingValues(horizontal = spacing16),
    content: @Composable RowScope.() -> Unit
) {
    AppButton(
        onClick = onClick,
        buttonType = ButtonType.Secondary,
        modifier = modifier,
        shape = shape,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(smallShape),
    contentPadding: PaddingValues = PaddingValues(horizontal = spacing16),
    content: @Composable RowScope.() -> Unit
) {
    AppButton(
        onClick = onClick,
        buttonType = ButtonType.Primary,
        modifier = modifier,
        shape = shape,
        contentPadding = contentPadding,
        content = content
    )
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewSecondaryButton() {
    VKRuStoreTheme {
        SecondaryButton(
            onClick = {}
        ) {
            Text(
                text = "Download",
                style = TextStyles.LabelMedium
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewPrimaryButton() {
    VKRuStoreTheme {
        PrimaryButton(
            onClick = {}
        ) {
            Text(
                text = "Download",
                style = TextStyles.LabelMedium
            )
        }
    }
}