package com.example.vkrustore.uikit.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.smallShape
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(smallShape),
        contentPadding = PaddingValues(horizontal = spacing16),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier
            .height(38.dp)
    ) {
        Text(
            text = text,
            style = TextStyles.LabelMedium
        )
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewAppButton() {
    VKRuStoreTheme {
        AppButton(
            text = "Download",
            onClick = {}
        )
    }
}