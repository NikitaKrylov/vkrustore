package com.example.vkrustore.feature.account.impl

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val accountImplModule = module {
    viewModelOf(::AccountViewModel)
}