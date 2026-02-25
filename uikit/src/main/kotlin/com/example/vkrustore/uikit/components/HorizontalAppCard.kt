package com.example.vkrustore.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.mediumShape
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing2
import com.example.vkrustore.uikit.spacing4
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun HorizontalAppCard(
    title: String,
    description: String,
    actionType: String,
    onClick: () -> Unit,
    imageUrl: String,
    modifier: Modifier = Modifier,
    rating: String? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(64.dp),
            model = imageUrl,
            contentDescription = "app icon",
            contentScale = ContentScale.Crop,
            error = {
                AppImageError(
                    shape = RoundedCornerShape(mediumShape)
                )
            }
        )

        Spacer(Modifier.size(spacing12))

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing2)
        ) {
            Text(
                text = title,
                style = TextStyles.TitleSmall
            )

            Text(
                text = description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyles.LabelSmall
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing4)
            ) {
                val textStyle = TextStyles.LabelLarge
                val iconSize = with(LocalDensity.current) {
                    textStyle.fontSize.toDp() - 4.dp
                }

                rating?.let {
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_24),
                        contentDescription = "rating icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .size(iconSize)
                    )

                    Text(
                        text = rating,
                        style = TextStyles.LabelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(Modifier.size(spacing12))

        SecondaryButton(
            text = "Скачать",
            onClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HorizontalAppCardPreviewLight() {
    VKRuStoreTheme {
        HorizontalAppCard(
            title = "App name",
            description = "best app",
            rating = "5+",
            actionType = "action type",
            onClick = { },
            imageUrl = ""
        )
    }
}

