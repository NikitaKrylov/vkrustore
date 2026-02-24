package com.example.vkrustore.data.apps.temp

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class ApkFileProvider(
    private val context: Context
) {

    fun getUri(file: File): Uri =
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

    fun getApkDownloadDir(): File {

        val dir = File(
            context.getExternalFilesDir(null),
            "Download"
        )

        if (!dir.exists()) {
            dir.mkdirs()
        }

        return dir
    }
}