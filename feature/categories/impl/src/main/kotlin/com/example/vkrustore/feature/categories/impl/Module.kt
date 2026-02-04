package com.example.vkrustore.feature.categories.impl

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val categoriesImplModule = module {
    viewModelOf(::CategoriesViewModel)
}