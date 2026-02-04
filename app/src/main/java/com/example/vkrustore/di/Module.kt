package com.example.vkrustore.di

import com.example.vkrustore.core.storage.storageModule
import com.example.vkrustore.data.apps.appsModule
import com.example.vkrustore.feature.account.impl.accountImplModule
import com.example.vkrustore.feature.appDetail.impl.appDetailImplModule
import com.example.vkrustore.feature.categories.impl.categoriesImplModule
import com.example.vkrustore.feature.onboarding.impl.onboardingModule
import com.example.vkrustore.feature.search.impl.searchImplModule
import com.example.vkrustore.feature.showcase.impl.showcaseImplModule
import org.koin.dsl.module

val rootModule = module {
    includes(
        storageModule,
    )

    includes(
        appsModule,
    )

    includes(
        onboardingModule,
        showcaseImplModule,
        accountImplModule,
        appDetailImplModule,
        categoriesImplModule,
        searchImplModule,
    )

}