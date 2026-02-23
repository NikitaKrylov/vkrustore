package com.example.vkrustore.data.apps.domain.models

import java.io.File

sealed interface DownloadApkState {
    data object Initial : DownloadApkState
    data class InProgress(
        val progress: Int,
    ) : DownloadApkState
    data class Success(
        val file: File,
    ) : DownloadApkState
    data class Error(
        val message: String,
    ) : DownloadApkState
}