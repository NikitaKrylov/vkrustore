package com.example.vkrustore.feature.showcase.api.models

import com.example.vkrustore.feature.common.models.AppPreview

sealed interface ShowcaseBlock {
    val id: Long

    data class ExpandedApp(
        override val id: Long,
        val title: String,
        val description: String,
        val head: String? = null,
        val subhead: String? = null
    ) : ShowcaseBlock

    data class AppsGroup(
        override val id: Long,
        val title: String,
        val apps: List<AppPreview>,
        val subtitle: String? = null
    ) : ShowcaseBlock
}