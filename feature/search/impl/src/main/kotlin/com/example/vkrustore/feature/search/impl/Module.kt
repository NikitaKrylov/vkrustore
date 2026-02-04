package com.example.vkrustore.feature.search.impl

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchImplModule = module {
    viewModelOf(::SearchViewModel)
}