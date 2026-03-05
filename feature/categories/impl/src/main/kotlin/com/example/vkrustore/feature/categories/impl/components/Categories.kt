package com.example.vkrustore.feature.categories.impl.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.vkrustore.feature.categories.impl.state.CategoriesState
import com.example.vkrustore.feature.categories.impl.state.CategoryItem
import com.example.vkrustore.uikit.TextStyles
import kotlin.random.Random

@Composable
internal fun Categories(
    state: CategoriesState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(state.items) { item ->
            CategoryCard(
                title = item.title,
                counter = item.appsCount,
                color = item.color,
                onClick = {},
            )
        }
    }
}


@Composable
private fun CategoryCard(
    title: String,
    counter: Int,
    modifier: Modifier = Modifier,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .requiredHeight(100.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = TextStyles.TitleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = counter.toString(),
                style = TextStyles.TitleLarge
            )
        }
    }
}

@Preview
@Composable
private fun CategoriesPreview() {
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
    )
    Categories(
        state = CategoriesState(
            items = List(20) {
                CategoryItem(
                    title = LoremIpsum(2).values.first(),
                    appsCount = Random.nextInt(0, 100_000),
                    color = colors.random(),
                )
            }
        )
    )
}