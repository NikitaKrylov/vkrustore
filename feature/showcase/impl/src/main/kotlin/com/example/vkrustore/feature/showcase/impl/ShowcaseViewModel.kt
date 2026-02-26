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
import com.example.vkrustore.feature.showcase.impl.state.SideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
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

    private val mutableSideEffect = MutableSharedFlow<SideEffect>()
    val sideEffect = mutableSideEffect.asSharedFlow()

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

    fun processAction(action: ShowcaseAction) = viewModelScope.launch {
        when (action) {
            ShowcaseAction.OnRefresh -> refresh()
            is ShowcaseAction.OnAppClick -> mutableSideEffect.emit(
                SideEffect.NavigateToAppDetail(action.appId)
            )
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

            val apps = appsRepository.getAll()

            val blocks = apps.mapIndexed { index, app ->
                if (index % 2 == 0) {
                    ShowcaseBlock.ExpandedApp(
                        id = app.id,
                        title = app.name,
                        description = app.description,
                        head = "Head banner",
                        subhead = "Subhead banner",
                        rating = app.rating,
                        bannerImageUrl = "",
                        appImageUrl = app.appIconUrl
                    )
                } else {
                    ShowcaseBlock.AppsGroup(
                        title = "Group: $index",
                        subtitle = "Sub title",
                        apps = List(9) {
                            AppPreview(
                                id = app.id,
                                title = app.name,
                                description = app.description,
                                rating = app.rating,
                                imageUrl = app.appIconUrl
                            )
                        }
                    )
                }
            }.filter { it.title.contains(query, ignoreCase = true) }

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
