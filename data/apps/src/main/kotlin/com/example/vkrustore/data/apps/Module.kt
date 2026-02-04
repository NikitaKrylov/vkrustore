package com.example.vkrustore.data.apps

import com.example.vkrustore.data.apps.repository.AppsRepository
import com.example.vkrustore.data.apps.repository.AppsRepositoryImpl
import org.koin.dsl.module

val appsModule = module {
    single<AppsRepository> {
        AppsRepositoryImpl()
    }
}