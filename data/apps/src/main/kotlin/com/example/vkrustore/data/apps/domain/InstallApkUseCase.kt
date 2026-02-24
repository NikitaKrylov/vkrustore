package com.example.vkrustore.data.apps.domain

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.vkrustore.data.apps.domain.models.InstallApkError
import com.example.vkrustore.data.apps.domain.models.InstallApkState
import com.example.vkrustore.data.apps.temp.ApkFileProvider
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class InstallApkUseCase(
    private val context: Context,
    private val fileProvider: ApkFileProvider,
) {

    operator fun invoke(file: File): Flow<InstallApkState> = flow {
        try {
            Log.i(TAG, "Installing file: ${file.absolutePath}")

            if (!file.exists()) {
                emit(
                    InstallApkState.Error(
                        message = "File ${file.absolutePath} doesnt exists",
                        type = InstallApkError.FileNotFound,
                    )
                )
                awaitCancellation()
            }

            checkInstallPermission()
            val uri = fileProvider.getUri(file)
            val intent = Intent(Intent.ACTION_VIEW).apply {

                setDataAndType(
                    uri,
                    "application/vnd.android.package-archive"
                )

                flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
            }

            context.startActivity(intent)

            Log.i(TAG,"Installer started")

            emit(InstallApkState.Success)
            awaitCancellation()

        } catch (e: SecurityException) {
            emit(
                InstallApkState.Error(
                    message = e.message ?: "Install failed due permission error",
                    type = InstallApkError.PermissionNotGranted,
                )
            )
            awaitCancellation()
        } catch (e: Exception) {
            Log.i(TAG,"Install failed", e)
            emit(
                InstallApkState.Error(
                    message = e.message ?: "Install failed due to unknown error",
                    type = InstallApkError.Unknown,
                )
            )
            awaitCancellation()
        }
    }

    private fun checkInstallPermission() {
        context.packageManager.canRequestPackageInstalls().also { isGranted ->
            Log.i(TAG, "Install permission allowed=$isGranted")

            if (!isGranted) {
                throw SecurityException("Install unknown apps permission not granted")
            }
        }
    }

    companion object {
        private const val TAG = "ApkInstallation"
    }
}