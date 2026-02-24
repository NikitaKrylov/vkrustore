package com.example.vkrustore.data.apps.domain

import android.app.DownloadManager
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.vkrustore.data.apps.domain.models.DownloadApkState
import com.example.vkrustore.data.apps.temp.ApkFileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.File



class DownloadApkUseCase(
    private val downloadManager: DownloadManager,
    private val fileProvider: ApkFileProvider,
) {

    companion object {
        private const val POLL_INTERVAL = 500L
        private const val TAG = "ApkInstallation"

    }

    suspend operator fun invoke(
        url: String,
        onProgress: (Int) -> Unit = {}
    ): Flow<DownloadApkState> = withContext(Dispatchers.IO) {
        flow {
            emit(DownloadApkState.Initial)
            try {
                Log.i(TAG, "Starting download: $url")

                val fileName = "app_${System.currentTimeMillis()}.apk"

                val targetDir = fileProvider.getApkDownloadDir()

                val file = File(targetDir, fileName)

                val request = DownloadManager.Request(url.toUri())
                    .setTitle(fileName)
                    .setNotificationVisibility(
                        DownloadManager.Request.VISIBILITY_VISIBLE
                    )
                    .setDestinationUri(Uri.fromFile(file))
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)

                val downloadId = downloadManager.enqueue(request)

                Log.i(TAG,"Download enqueued id=$downloadId")

                emitAll(
                    monitorDownload(downloadId, file)
                )
            } catch (e: Exception) {
                emit(DownloadApkState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    private fun monitorDownload(
        downloadId: Long,
        file: File,
    ): Flow<DownloadApkState> = flow {
        val query = DownloadManager.Query()
            .setFilterById(downloadId)

        while (true) {

            val cursor: Cursor =
                downloadManager.query(query)

            if (!cursor.moveToFirst()) {
                cursor.close()
                emit(DownloadApkState.Error("Download file not found"))
            }

            val status =
                cursor.getInt(
                    cursor.getColumnIndexOrThrow(
                        DownloadManager.COLUMN_STATUS
                    )
                )

            val total =
                cursor.getLong(
                    cursor.getColumnIndexOrThrow(
                        DownloadManager.COLUMN_TOTAL_SIZE_BYTES
                    )
                )

            val downloaded =
                cursor.getLong(
                    cursor.getColumnIndexOrThrow(
                        DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR
                    )
                )

            if (total > 0) {
                val progress = ((downloaded * 100) / total).toInt()
                emit(DownloadApkState.InProgress(progress))
            }

            when (status) {
                DownloadManager.STATUS_SUCCESSFUL -> {
                    cursor.close()
                    Log.i(TAG, "Download success: ${file.absolutePath}")
                    emit(DownloadApkState.Success(file))
                    awaitCancellation()
                }

                DownloadManager.STATUS_FAILED -> {
                    cursor.close()
                    Log.i(TAG, "Download failed")
                    emit(DownloadApkState.Error("Download failed"))
                }
            }

            cursor.close()

            delay(POLL_INTERVAL)
        }
    }
}