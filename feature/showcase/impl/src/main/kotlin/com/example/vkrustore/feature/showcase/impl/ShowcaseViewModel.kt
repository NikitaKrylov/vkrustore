package com.example.vkrustore.feature.showcase.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkrustore.data.apps.repository.AppsRepository
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.ShowcaseAction
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.feature.showcase.impl.state.MainShowcaseState
import com.example.vkrustore.feature.showcase.impl.state.SearchState
import com.example.vkrustore.feature.showcase.impl.state.ShowcaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ShowcaseViewModel(
    private val appsRepository: AppsRepository
) : ViewModel() {
    private val _searchStateFlow: MutableStateFlow<SearchState> = MutableStateFlow(SearchState(""))
    private val _showcaseStateFlow: MutableStateFlow<ShowcaseState> = MutableStateFlow(ShowcaseState.Loading)

    val uiStateFlow = combine(
        _searchStateFlow,
        _showcaseStateFlow
    ) { searchState, showcaseState ->
        MainShowcaseState(
            searchState = searchState,
            showcaseState = showcaseState
        )
    }
        .onStart { requestQuery("") }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = MainShowcaseState(
                searchState = SearchState(""),
                showcaseState = ShowcaseState.Loading
            )
        )

    fun processAction(action: ShowcaseAction) {
        when (action) {
            ShowcaseAction.OnRefresh -> refresh()
            is ShowcaseAction.OnAppClick -> TODO()
            is ShowcaseAction.OnSearch -> search(action.query)
            ShowcaseAction.OnClearSearch -> clearSearch()
        }
    }

    private fun refresh() {
        search(_searchStateFlow.value.query)
    }

    private fun search(query: String) {
        viewModelScope.launch {
            _searchStateFlow.update {
                it.copy(query = query)
            }

            requestQuery(query)
        }
    }

    private fun clearSearch() {
        viewModelScope.launch {
            _searchStateFlow.update {
                it.copy(query = "")
            }
        }
    }

    private fun requestQuery(query: String) {
        viewModelScope.launch {
            _showcaseStateFlow.emit(ShowcaseState.Loading)

            val blocks = List(10) { id ->
                if (id % 2 == 0) {
                    ShowcaseBlock.ExpandedApp(
                        id = (id).toString(),
                        title = "Title app",
                        description = "best app",
                        head = "Head banner",
                        subhead = "Subhead banner",
                        rating = 5f,
                        bannerImageUrl = "",
                        appImageUrl = ""
                    )
                } else {
                    ShowcaseBlock.AppsGroup(
                        title = "Group: ${id}",
                        subtitle = "Sub title",
                        apps = List(9) { id ->
                            AppPreview(
                                id = id.toString(),
                                title = "Title app",
                                description = "best app",
                                rating = 5f,
                                imageUrl = ""
                            )
                        }
                    )
                }
            }
                .filter { it.title.contains(query, ignoreCase = true) }

            if (blocks.isNotEmpty()) {
                _showcaseStateFlow.emit(
                    ShowcaseState.Show(
                        blocks = blocks,
                        isRefreshing = false
                    )
                )
            } else {
                _showcaseStateFlow.emit(
                    ShowcaseState.Error(
                        "По запросу «$query» ничего не найдено"
                    )
                )
            }
        }
    }
}
