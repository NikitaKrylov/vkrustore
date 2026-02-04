package com.example.vkrustore.feature.appDetail.impl

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appDetailImplModule = module {
    viewModelOf(::AppDetailViewModel)
}