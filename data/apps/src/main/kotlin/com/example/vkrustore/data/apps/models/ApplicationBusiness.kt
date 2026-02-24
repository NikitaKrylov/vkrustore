package com.example.vkrustore.data.apps.models


data class ApplicationBusiness(
    val id: String,
    val name: String,
    val category: String,
    val rating: Float?,
    val ratingCount: Int?,
    val description: String,
    val apkSourceUrl: String,
    val appIconUrl: String,
    val bannerImageUrl: String? = null,
)