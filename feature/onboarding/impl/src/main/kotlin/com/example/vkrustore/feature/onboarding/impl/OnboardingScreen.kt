package com.example.vkrustore.feature.onboarding.impl

import androidx.compose.runtime.Composable
import com.example.vkrustore.feature.onboarding.impl.components.Onboarding
import org.koin.androidx.compose.koinViewModel
import com.example.vkrustore.feature.common.compose.ObserveAsEvents
import com.example.vkrustore.feature.onboarding.api.OnboardingSideEffect


@Composable
fun OnboardingScreen(
    onFinishOnboarding: () -> Unit,
) {
    val viewModel: OnboardingViewModel = koinViewModel()

    Onboarding(
        onAction = viewModel::processAction,
    )

    ObserveAsEvents(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is OnboardingSideEffect.FinishOnboarding -> onFinishOnboarding()
        }
    }

}