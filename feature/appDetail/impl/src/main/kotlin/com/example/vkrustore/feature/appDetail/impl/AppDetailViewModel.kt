package com.example.vkrustore.feature.appDetail.impl

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.palette.graphics.Palette
import com.example.vkrustore.data.apps.domain.DownloadApkUseCase
import com.example.vkrustore.data.apps.domain.InstallApkUseCase
import com.example.vkrustore.data.apps.domain.models.DownloadApkState
import com.example.vkrustore.data.apps.domain.models.InstallApkState
import com.example.vkrustore.data.apps.repository.AppsRepository
import com.example.vkrustore.feature.appDetail.api.AppDetailRoute
import com.example.vkrustore.feature.appDetail.impl.state.Actions
import com.example.vkrustore.feature.appDetail.impl.state.AppStatus
import com.example.vkrustore.feature.appDetail.impl.state.SideEffect
import com.example.vkrustore.feature.appDetail.impl.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            is Actions.CalcDominantColor -> calcDominantColor(action.bitmap)
        }
    }

    private suspend fun downloadApk() {
        currentApp?.let { app ->
            downloadApkUseCase(app.apkSourceUrl).collect {
                when (it) {
                    is DownloadApkState.Error -> {
                        mutableSideEffect.emit(SideEffect.ShowToast("Произошла ошибка во время скачивания"))
                    }

                    is DownloadApkState.InProgress -> {
                        mutableState.update { currentState ->
                            (currentState as UiState.ShowApp).copy(
                                status = AppStatus.Downloading
                            )
                        }
                    }

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
                    mutableSideEffect.emit(SideEffect.ShowToast("Произошла ошибка во время установки"))
                }

                InstallApkState.InProgress -> {
                    mutableState.update { currentState ->
                        (currentState as UiState.ShowApp).copy(
                            status = AppStatus.Installing
                        )
                    }
                }

                InstallApkState.Initial -> {}
                InstallApkState.Success -> {
                    mutableState.update { currentState ->
                        (currentState as UiState.ShowApp).copy(
                            status = AppStatus.Installed
                        )
                    }
                }
            }
        }
    }

    private suspend fun calcDominantColor(bitmap: Bitmap) {
        val palette = withContext(Dispatchers.Default) {
            Palette.from(bitmap).generate()
        }

        palette.dominantSwatch?.rgb?.let {
            mutableState.update { currentState ->
                (currentState as UiState.ShowApp).copy(
                    dominantColor = Color(it)
                )
            }
        }
    }
}