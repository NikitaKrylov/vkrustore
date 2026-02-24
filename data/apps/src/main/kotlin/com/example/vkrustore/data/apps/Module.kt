package com.example.vkrustore.data.apps

import android.app.DownloadManager
import com.example.vkrustore.data.apps.repository.AppsRepository
import com.example.vkrustore.data.apps.repository.AppsRepositoryImpl
import com.example.vkrustore.data.apps.temp.ApkFileProvider
import com.example.vkrustore.data.apps.domain.DownloadApkUseCase
import com.example.vkrustore.data.apps.domain.InstallApkUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appsModule = module {
    single<AppsRepository> {
        AppsRepositoryImpl()
    }

    single {
        androidContext()
            .getSystemService(
                DownloadManager::class.java
            )
    }

    single {
        ApkFileProvider(get())
    }

    factory {
        DownloadApkUseCase(
            get(),
            get(),
        )
    }

    factory {
        InstallApkUseCase(
            get(),
            get(),
        )
    }
}