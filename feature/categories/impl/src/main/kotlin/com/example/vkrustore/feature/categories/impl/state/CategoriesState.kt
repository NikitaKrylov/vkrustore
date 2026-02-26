package com.example.vkrustore.feature.categories.impl.state

import androidx.compose.ui.graphics.Color
import org.koin.viewmodel.emptyState

internal data class CategoriesState(
    val items: List<CategoryItem> = emptyList()
)

internal data class CategoryItem(
    val title: String,
    val appsCount: Int,
    val color: Color,
)
