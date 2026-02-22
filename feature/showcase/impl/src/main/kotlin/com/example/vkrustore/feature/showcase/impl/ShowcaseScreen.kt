package com.example.vkrustore.feature.showcase.impl

import androidx.compose.runtime.Composable
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.feature.showcase.api.models.ShowcaseBlock
import com.example.vkrustore.feature.showcase.impl.components.Showcase
import com.example.vkrustore.feature.showcase.impl.state.MainShowcaseState
import com.example.vkrustore.feature.showcase.impl.state.SearchState
import com.example.vkrustore.feature.showcase.impl.state.ShowcaseState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowcaseScreen(
) {
    val viewModel: ShowcaseViewModel = koinViewModel()

    val blocks = List(4) { id ->
        if (id % 2 == 0) {
            ShowcaseBlock.ExpandedApp(
                id = id.toLong(),
                title = "Title app",
                description = "best app",
                head = "Head banner",
                subhead = "Subhead banner"
            )
        } else {
            ShowcaseBlock.AppsGroup(
                title = "Group: $id",
                subtitle = "Sub title",
                apps = List(9) { id ->
                    AppPreview(
                        id = id.toLong(),
                        title = "Title app",
                        description = "best app"
                    )
                }
            )
        }
    }

    Showcase(
        state = MainShowcaseState(
            searchState = SearchState(""),
            showcaseState = ShowcaseState.Show(blocks = blocks)
        )
    )
}
