package com.example.vkrustore.feature.categories.impl

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.vkrustore.feature.categories.impl.state.CategoriesState
import com.example.vkrustore.feature.categories.impl.state.CategoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class CategoriesViewModel : ViewModel() {
    private val mutableState = MutableStateFlow(CategoriesState())
    val state = mutableState.asStateFlow()

    init {
        mutableState.update {
            it.copy(
                items = List(20) {
                    CategoryItem(
                        title = "Category $it",
                        appsCount = 100,
                        color = Color.Cyan,
                    )
                }
            )
        }
    }
}