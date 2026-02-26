package com.example.vkrustore.feature.appDetail.impl

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import com.example.vkrustore.data.apps.domain.DownloadApkUseCase
import com.example.vkrustore.data.apps.domain.InstallApkUseCase
import com.example.vkrustore.data.apps.domain.models.DownloadApkState
import com.example.vkrustore.data.apps.domain.models.InstallApkState
import com.example.vkrustore.data.apps.repository.AppsRepository
import com.example.vkrustore.feature.appDetail.api.AppDetailRoute
import com.example.vkrustore.feature.appDetail.impl.state.Actions
import com.example.vkrustore.feature.appDetail.impl.state.SideEffect
import com.example.vkrustore.feature.appDetail.impl.state.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File

internal class AppDetailViewModel(
    savedStateHandle: SavedStateHandle,
    appsRepository: AppsRepository,
    private val installApkUseCase: InstallApkUseCase,
    private val downloadApkUseCase: DownloadApkUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow<UiState>(UiState.Initialize)
    val state = mutableState.asStateFlow()

    private val mutableSideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect = mutableSideEffect.asSharedFlow()

    private val appId = savedStateHandle.toRoute<AppDetailRoute>().appId
    private val currentApp = appsRepository.findById(appId)

    init {
        currentApp?.apply {
            mutableState.tryEmit(
                UiState.ShowApp(
                    name = name,
                    description = description,
                    appIconUrl = appIconUrl,
                    category = category,
                    screenshots = screens,
                    devName = "The best dev",
                    ratingAge = 18,
                    apkSize = "100мб",
                    installCount = "100 000",
                    ratingCount = 100_000,
                    rating = 4.5f,
                )
            )
        }
    }

    fun processAction(action: Actions) = viewModelScope.launch {
        when (action) {
            Actions.NavigateBack -> mutableSideEffect.emit(SideEffect.NavigateBack)
            Actions.Submit -> downloadApk()
        }
    }

    private suspend fun downloadApk() {
        currentApp?.let { app ->
            downloadApkUseCase(app.apkSourceUrl).collect {
                when (it) {
                    is DownloadApkState.Error -> {
                        mutableSideEffect.emit(SideEffect.ShowToast(it.message))
                    }
                    is DownloadApkState.InProgress -> {}
                    DownloadApkState.Initial -> {}
                    is DownloadApkState.Success -> installApp(it.file)
                }
            }
        }
    }


    private suspend fun installApp(file: File) {
        installApkUseCase(file).collect {
            when (it) {
                is InstallApkState.Error -> {
                    mutableSideEffect.emit(SideEffect.ShowToast(it.message))
                }
                InstallApkState.InProgress -> {}
                InstallApkState.Initial -> {}
                InstallApkState.Success -> {}
            }
        }
    }
}