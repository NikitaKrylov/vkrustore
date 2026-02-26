package com.example.vkrustore.uikit.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.spacing2
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun ExpandableDescription(
    text: String,
    minLines: Int = 6,
    maxLines: Int = Int.MAX_VALUE
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(spacing2),
        horizontalAlignment = Alignment.End
    ) {

        Text(
            text = text,
            maxLines = if (expanded) maxLines else minLines,
            overflow = TextOverflow.Ellipsis,
            style = TextStyles.BodyMedium
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
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam auctor pretium turpis. " +
                    "Pellentesque eleifend tincidunt enim porttitor aliquam. Suspendisse a fringilla elit, eu aliquam " +
                    "libero. Vestibulum volutpat massa diam, ut feugiat velit molestie ut. Aenean sit amet gravida felis" +
                    ". Vestibulum scelerisque felis at turpis tincidunt, a feugiat mauris mollis. Vestibulum ante ipsum " +
                    "primis in faucibus orci luctus et ultrices posuere cubilia curae; Class aptent taciti sociosqu ad " +
                    "litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse dignissim nisl eu metus " +
                    "cursus iaculis. Pellentesque iaculis et lectus sed iaculis. Sed porta nisi dolor, venenatis auctor " +
                    "erat lacinia vel. Donec finibus ac."
        )
    }
}