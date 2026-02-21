package com.example.vkrustore.feature.showcase.impl

import androidx.compose.runtime.Composable
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.feature.showcase.impl.components.Showcase
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowcaseScreen(
) {
    val viewModel: ShowcaseViewModel = koinViewModel()

    Showcase(
        blocks = TODO()
    )
}
