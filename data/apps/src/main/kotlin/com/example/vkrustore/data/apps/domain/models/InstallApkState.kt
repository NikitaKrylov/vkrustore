package com.example.vkrustore.data.apps.domain.models

sealed interface InstallApkState {
    data object Initial : InstallApkState
    data object InProgress : InstallApkState
    data object Success : InstallApkState
    data class Error(
        val message: String,
        val type: InstallApkError,
    ) : InstallApkState
}

enum class InstallApkError {
    FileNotFound,
    PermissionNotGranted,
    Unknown,
}
