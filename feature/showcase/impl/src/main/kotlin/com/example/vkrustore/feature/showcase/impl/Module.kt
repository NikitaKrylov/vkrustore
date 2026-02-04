package com.example.vkrustore.feature.showcase.impl

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val showcaseImplModule = module {
    viewModelOf(::ShowcaseViewModel)
}