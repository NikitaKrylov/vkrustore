package com.example.vkrustore.feature.showcase.api.models

import com.example.vkrustore.feature.common.models.AppPreview

sealed interface ShowcaseBlock {
    val title: String

    data class ExpandedApp(
        val id: String,
        override val title: String,
        val description: String,
        val bannerImageUrl: String,
        val appImageUrl: String,
        val rating: Float?,
        val head: String? = null,
        val subhead: String? = null,
    ) : ShowcaseBlock

    data class AppsGroup(
        override val title: String,
        val apps: List<AppPreview>,
        val subtitle: String? = null
    ) : ShowcaseBlock
}
