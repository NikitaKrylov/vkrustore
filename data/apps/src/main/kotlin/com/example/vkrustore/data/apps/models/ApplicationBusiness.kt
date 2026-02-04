package com.example.vkrustore.data.apps.models

data class ApplicationBusiness(
    val id: Long,
    val name: String,
    val category: String,
    val rating: Float?,
    val ratingCount: Int?,
    val description: String,
)