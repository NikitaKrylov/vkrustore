package com.example.vkrustore.feature.showcase.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowcaseScreen() {
    val viewModel: ShowcaseViewModel = koinViewModel()

}