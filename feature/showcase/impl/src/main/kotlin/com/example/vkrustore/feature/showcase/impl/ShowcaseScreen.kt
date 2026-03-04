package com.example.vkrustore.feature.showcase.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vkrustore.feature.common.compose.ObserveAsEvents
import com.example.vkrustore.feature.showcase.impl.components.Showcase
import com.example.vkrustore.feature.showcase.impl.state.SideEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowcaseScreen(
    navigateToDetail: (String) -> Unit,
) {
    val viewModel: ShowcaseViewModel = koinViewModel()
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    Showcase(
        state = uiState,
        onAction = viewModel::processAction,
    )

    ObserveAsEvents(viewModel.sideEffect) { effect ->
        when (effect) {
            is SideEffect.NavigateToAppDetail -> navigateToDetail(effect.appId)
        }
    }
}
