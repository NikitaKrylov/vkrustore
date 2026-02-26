package com.example.vkrustore.feature.appDetail.api.models

data class ApplicationDetails(
    val id: String,
    val name: String,
    val category: String,
    val rating: Float?,
    val ratingCount: Int?,
    val description: String,
    val installCount: String,
    val apkSourceUrl: String,
    val apkSize: String,
    val appIconUrl: String,
    val ratingAge: Int,
    val devName: String,
    val screenshots: List<String>
)
