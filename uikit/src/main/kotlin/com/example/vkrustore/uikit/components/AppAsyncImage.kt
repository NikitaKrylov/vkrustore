package com.example.vkrustore.uikit.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter.State
import coil3.compose.SubcomposeAsyncImage
import com.example.vkrustore.uikit.IconAppShape

@Composable
fun AppAsyncImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    onSuccess: ((State.Success) -> Unit)? = null,
    shape: RoundedCornerShape = IconAppShape
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .fillMaxSize()
            .clip(shape),
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        onSuccess = onSuccess,
        error = {
            AppImageError(shape = shape)
        }
    )
}