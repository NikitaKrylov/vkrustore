package com.example.vkrustore.feature.onboarding.impl

import androidx.lifecycle.ViewModel
import com.example.vkrustore.feature.onboarding.api.OnboardingAction
import com.example.vkrustore.feature.onboarding.api.OnboardingSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class OnboardingViewModel : ViewModel() {
    private val mutableSideEffect = MutableSharedFlow<OnboardingSideEffect>()
    val sideEffect = mutableSideEffect.asSharedFlow()

    fun processAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.Finish -> finishOnboarding()
        }
    }

    private fun finishOnboarding() {

    }

}