package com.example.vkrustore.data.apps.repository

import com.example.vkrustore.data.apps.models.ApplicationBusiness

interface AppsRepository {
    fun findById(appId: String): ApplicationBusiness?
    fun getByCategory(category: String): List<ApplicationBusiness>
    fun getAll(): List<ApplicationBusiness>
}