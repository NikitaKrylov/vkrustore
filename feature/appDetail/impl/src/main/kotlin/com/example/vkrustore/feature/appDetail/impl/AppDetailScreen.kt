package com.example.vkrustore.feature.appDetail.impl

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vkrustore.feature.appDetail.impl.components.AppDetail
import com.example.vkrustore.feature.appDetail.impl.state.SideEffect
import com.example.vkrustore.feature.appDetail.impl.state.UiState
import com.example.vkrustore.feature.common.compose.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppDetailScreen(
    onBack: () -> Unit,
    showMessage: (String) -> Unit,
) {
    val context = LocalContext.current

    val viewModel: AppDetailViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value


    when (state) {
        UiState.Initialize -> {}
        is UiState.ShowApp -> {
            AppDetail(
                state = state,
                onAction = viewModel::processAction,
            )
        }
    }

    ObserveAsEvents(viewModel.sideEffect) { effect ->
        when (effect) {
            SideEffect.NavigateBack -> onBack()
            is SideEffect.ShowToast -> showMessage(effect.message)
        }
    }
}
