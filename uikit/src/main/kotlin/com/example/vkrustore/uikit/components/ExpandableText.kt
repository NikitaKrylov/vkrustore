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
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 6
) {
    var isOverflow by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(spacing2),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            maxLines = Int.MAX_VALUE.takeIf { expanded } ?: maxLines,
            overflow = TextOverflow.Ellipsis,
            style = TextStyles.BodyMedium,
            textAlign = TextAlign.Start,
            onTextLayout = { layoutResult ->
                if (!expanded) {
                    isOverflow = layoutResult.hasVisualOverflow
                }
            }
        )

        if (isOverflow) {
            Text(
                text = "Скрыть".takeIf { expanded } ?: "Еще",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { expanded = !expanded }
            )
        }
    }
}

@Preview
@Composable
fun PreviewExpandableText() {
    VKRuStoreTheme {
        ExpandableText(
            maxLines = 2,
            text = "Lorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsum"
        )
    }
}