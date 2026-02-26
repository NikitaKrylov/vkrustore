package com.example.vkrustore.feature.common.ext

import android.content.Context
import android.content.pm.PackageManager

fun Context.isAppInstalled(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}