package com.example.vkrustore.feature.onboarding.api

import com.example.vkrustore.feature.common.state.BaseAction

sealed interface OnboardingAction : BaseAction {
    data object Finish : OnboardingAction
}