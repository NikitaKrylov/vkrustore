package com.example.vkrustore.feature.onboarding.api

sealed class OnboardingSideEffect {
    data object FinishOnboarding : OnboardingSideEffect()
    data object RequestPermission : OnboardingSideEffect()
}
