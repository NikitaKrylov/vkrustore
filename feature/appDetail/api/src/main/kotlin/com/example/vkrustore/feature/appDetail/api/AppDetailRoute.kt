package com.example.vkrustore.feature.appDetail.api

import kotlinx.serialization.Serializable


@Serializable
data class AppDetailRoute(
    val appId: String,
)