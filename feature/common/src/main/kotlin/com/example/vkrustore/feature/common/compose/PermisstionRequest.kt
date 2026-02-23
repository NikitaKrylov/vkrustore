package com.example.vkrustore.feature.common.compose

import android.content.Intent
import android.os.Build
import android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle

@Stable
class InstallPermissionState(
    val isGranted: () -> Boolean,
    val requestPermission: () -> Unit
)

@Composable
fun rememberInstallPermissionState(
    onPermissionGranted: () -> Unit,
): InstallPermissionState {
    val context = LocalContext.current

    fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.packageManager.canRequestPackageInstalls()
        } else {
            true
        }
    }

    var granted by remember {
        mutableStateOf(checkPermission())
    }

    fun requestPermission() {
        if (checkPermission()) {
            onPermissionGranted()
            return
        }

        val intent = Intent(
            ACTION_MANAGE_UNKNOWN_APP_SOURCES,
            "package:${context.packageName}".toUri()
        )

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    return remember {
        InstallPermissionState(
            isGranted = { granted },
            requestPermission = ::requestPermission
        )
    }
}
