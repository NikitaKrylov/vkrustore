package com.example.vkrustore.feature.categories.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vkrustore.feature.categories.impl.components.Categories
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()


    Categories(
        state = state,
    )
}