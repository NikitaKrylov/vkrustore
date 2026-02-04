package com.example.vkrustore.feature.showcase.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.feature.common.models.AppPreview
import com.example.vkrustore.uikit.components.ExpandedAppCard
import com.example.vkrustore.uikit.components.HorizontalAppCard

@Composable
fun Showcase() {
    Column {

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalAppsGroup(
    title: String,
    subtitle: String? = null,
    pages: List<List<AppPreview>>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row {
            Column {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 21.sp
                )

                subtitle?.let {
                    Text(
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }


        HorizontalAppsPager(
            pages = pages,
        )
    }
}


@Composable
fun HorizontalAppsPager(
    pages: List<List<AppPreview>>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { pages.size }

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = true,
        pageSpacing = 16.dp,
        contentPadding = PaddingValues(8.dp),
        key = { it },
        modifier = modifier
            .fillMaxWidth()
    ) { index ->
        val items = pages[index]

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { item ->
                HorizontalAppCard(
                    title = item.title,
                    description = item.description,
                    rating = "TODO()",
                    actionType = "TODO()",
                    onClick = {  },
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowcasePreview() {
    Showcase()
}


@Preview
@Composable
private fun VerticalAppsGroupPreview() {
    val items = remember {
        List(20) { id ->
            AppPreview(
                id = id.toLong(),
                title = "TODO()",
                description = "TODO()"
            )
        }
    }
    val pages = remember(items) {
        items.chunked(3)
    }
    VerticalAppsGroup(
        title = "Самое интересное",
        subtitle = "Очень интересное описание",
        pages = pages
    )
}