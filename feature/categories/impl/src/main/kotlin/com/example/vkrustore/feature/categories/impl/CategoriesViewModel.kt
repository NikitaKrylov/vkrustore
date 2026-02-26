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
                items = listOf(
                    CategoryItem(
                        title = "Социальные сети",
                        appsCount = 87,
                        color = Color(0xFF4A90E2)
                    ),
                    CategoryItem(
                        title = "Игры",
                        appsCount = 100,
                        color = Color(0xFFE94E77)
                    ),
                    CategoryItem(
                        title = "Финансы",
                        appsCount = 42,
                        color = Color(0xFF27AE60)
                    ),
                    CategoryItem(
                        title = "Путешествия",
                        appsCount = 65,
                        color = Color(0xFFF5A623)
                    ),
                    CategoryItem(
                        title = "Музыка и аудио",
                        appsCount = 73,
                        color = Color(0xFF8E44AD)
                    ),
                    CategoryItem(
                        title = "Видео и стриминг",
                        appsCount = 58,
                        color = Color(0xFFD0021B)
                    ),
                    CategoryItem(
                        title = "Покупки",
                        appsCount = 91,
                        color = Color(0xFF16A085)
                    ),
                    CategoryItem(
                        title = "Образование",
                        appsCount = 36,
                        color = Color(0xFF2980B9)
                    ),
                    CategoryItem(
                        title = "Здоровье и фитнес",
                        appsCount = 49,
                        color = Color(0xFFFF6F61)
                    ),
                    CategoryItem(
                        title = "Инструменты",
                        appsCount = 77,
                        color = Color(0xFF34495E)
                    ),
                    CategoryItem(
                        title = "Развлечения",
                        appsCount = 84,
                        color = Color(0xFF9B59B6)
                    ),
                    CategoryItem(
                        title = "Новости",
                        appsCount = 29,
                        color = Color(0xFF2ECC71)
                    ),
                    CategoryItem(
                        title = "Фото и видео",
                        appsCount = 68,
                        color = Color(0xFFE67E22)
                    ),
                    CategoryItem(
                        title = "Работа и продуктивность",
                        appsCount = 54,
                        color = Color(0xFF1ABC9C)
                    ),
                    CategoryItem(
                        title = "Навигация",
                        appsCount = 33,
                        color = Color(0xFF7F8C8D)
                    )
                )
            )
        }
    }
}