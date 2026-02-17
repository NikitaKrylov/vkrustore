package com.example.vkrustore.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = {
                androidContext().preferencesDataStoreFile("app_prefs")
            }
        )
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single<KeyValueStorage> {
        KeyValueStorageImpl(get(), get())
    }
}
