package com.example.vkrustore.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class KeyValueStorageImpl(
    private val dataStore: DataStore<Preferences>,
    private val json: Json
): KeyValueStorage {
    override fun put(key: String, value: Any) {
        runBlocking {
            dataStore.edit { pref ->
                pref[stringPreferencesKey(key)] = json.encodeToString(serializer(value::class.java), value)
            }
        }
    }

    override fun putMany(values: Map<String, Any>) {
        values.forEach { (key, value) ->
            put(key, value)
        }
    }

    override fun removeAll() {
        runBlocking {
            dataStore.edit { it.clear() }
        }
    }

    override fun remove(vararg keys: String) {
        runBlocking {
            keys.forEach { key ->
                dataStore.edit {
                    it.remove(stringPreferencesKey(key))
                }
            }
        }
    }

    override fun <T> get(key: String, clazz: Class<T>): T? {
        return runBlocking {
            val strJson = dataStore.data
                .map { it[stringPreferencesKey(key)] }
                .first()

            strJson?.let {
                json.decodeFromString(
                    serializer(clazz),
                    it
                ) as? T
            }
        }
    }

    override fun getAll(): Map<String, *> {
        return runBlocking {
            dataStore.data
                .first()
                .asMap()
                .mapKeys { it.key.name }
        }
    }
}
