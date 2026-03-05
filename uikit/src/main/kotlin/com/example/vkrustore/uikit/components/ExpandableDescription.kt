package com.example.vkrustore.uikit.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.spacing2
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun ExpandableDescription(
    text: String,
    modifier: Modifier = Modifier,
    minLines: Int = 6,
    maxLines: Int = Int.MAX_VALUE
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(spacing2),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = text,
            maxLines = if (expanded) maxLines else minLines,
            overflow = TextOverflow.Ellipsis,
            style = TextStyles.BodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = if (expanded) "Скрыть" else "Еще",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { expanded = !expanded }
        )
    }
}

@Preview
@Composable
fun PreviewExpandableDescription() {
    VKRuStoreTheme {
        ExpandableDescription(
            text = "Lorem ipsum "
        )
    }
}