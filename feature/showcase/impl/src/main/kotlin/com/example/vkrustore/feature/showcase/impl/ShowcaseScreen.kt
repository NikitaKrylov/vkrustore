package com.example.vkrustore.feature.showcase.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fitOutside
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WindowInsetsRulers
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vkrustore.feature.common.compose.ObserveAsEvents
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.feature.showcase.impl.components.Showcase
import com.example.vkrustore.feature.showcase.impl.state.MainShowcaseState
import com.example.vkrustore.feature.showcase.impl.state.SearchState
import com.example.vkrustore.feature.showcase.impl.state.ShowcaseState
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
