package com.example.vkrustore.core.storage

interface KeyValueStorage {
    fun put(key: String, value: Any)
    fun putMany(values: Map<String, Any>)
    fun removeAll()
    fun remove(vararg keys: String)
    fun <T> get(key: String, clazz: Class<T>): T?
    fun getAll(): Map<String, *>
}
