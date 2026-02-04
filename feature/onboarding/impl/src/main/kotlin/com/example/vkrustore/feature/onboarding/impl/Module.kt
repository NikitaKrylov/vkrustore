package com.example.vkrustore.feature.onboarding.impl

import com.example.vkrustore.feature.onboarding.api.OnboardingRoute
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.scope.dsl.activityRetainedScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

@OptIn(KoinExperimentalAPI::class)
val onboardingModule = module {
    viewModelOf(::OnboardingViewModel)
}