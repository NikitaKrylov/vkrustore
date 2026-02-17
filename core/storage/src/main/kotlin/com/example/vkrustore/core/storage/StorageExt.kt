package com.example.vkrustore.core.storage

inline fun <reified T> KeyValueStorage.get(key: String): T? {
    return get(key, T::class.java)
}
