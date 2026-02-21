package com.example.vkrustore.feature.showcase.impl.state

sealed interface SearchState {
    val query: String

    data class Loading(
        override val query: String
    ) : SearchState

    data class Query(
        override val query: String
    ) : SearchState

    data class Error(
        override val query: String,
        val message: String? = null
    ) : SearchState

    data class Empty(
        override val query: String = ""
    ) : SearchState
}