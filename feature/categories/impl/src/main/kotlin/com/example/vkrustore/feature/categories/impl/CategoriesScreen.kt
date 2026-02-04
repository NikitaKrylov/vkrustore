package com.example.vkrustore.feature.categories.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = koinViewModel()
}