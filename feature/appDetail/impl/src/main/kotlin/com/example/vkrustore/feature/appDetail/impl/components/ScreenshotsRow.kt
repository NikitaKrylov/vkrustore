package com.example.vkrustore.feature.appDetail.impl.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.vkrustore.uikit.extraSmallShape
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing16

@Composable
fun ScreenshotsRow(
    screenshotsUrl: List<String>,
    onClick: (Int) -> Unit,
    aspectRatio: Float,
    modifier: Modifier = Modifier
) {
    val fixedHeight = 180.dp

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = spacing16),
        horizontalArrangement = Arrangement.spacedBy(spacing12)
    ) {
        itemsIndexed(screenshotsUrl) { index, url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .height(fixedHeight)
                    .aspectRatio(aspectRatio)
                    .clip(RoundedCornerShape(extraSmallShape))
                    .clickable { onClick(index) },
                contentScale = ContentScale.Crop
            )
        }
    }
}