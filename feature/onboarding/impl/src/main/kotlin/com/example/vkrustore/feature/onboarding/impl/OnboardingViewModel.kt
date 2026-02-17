package com.example.vkrustore.feature.onboarding.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkrustore.core.storage.KeyValueStorage
import com.example.vkrustore.core.storage.StorageKeys
import com.example.vkrustore.feature.onboarding.api.OnboardingAction
import com.example.vkrustore.feature.onboarding.api.OnboardingSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class OnboardingViewModel(
    private val storage: KeyValueStorage
) : ViewModel() {
    private val mutableSideEffect = MutableSharedFlow<OnboardingSideEffect>()
    val sideEffect = mutableSideEffect.asSharedFlow()

    fun processAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.Finish -> finishOnboarding()
        }
    }

    private fun finishOnboarding() {
        viewModelScope.launch {
            storage.put(StorageKeys.IsOnboarded, false)
            mutableSideEffect.emit(OnboardingSideEffect.FinishOnboarding)
        }
    }
}