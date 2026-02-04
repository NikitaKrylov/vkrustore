package com.example.vkrustore.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.PinnableContainer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.acos


@Composable
fun ExpandedAppCard(
    bannerHead: String,
    bannerSubhead: String,
    title: String,
    description: String,
    rating: String,
    appAction: String,
    containerColor: Color,
) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(containerColor)
            .height(300.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = bannerHead,
                fontSize = 24.sp,
            )

            Text(
                text = bannerSubhead,
                fontSize = 15.sp,
            )

            HorizontalAppCard(
                title = title,
                description = description,
                rating = rating,
                actionType = appAction,
                onClick = {},
            )
        }
    }

}


@Preview
@Composable
private fun ExpandedAppCardPreview() {
    ExpandedAppCard(
        bannerHead = "Banner head",
        bannerSubhead = "Banner subhead",
        title = "title",
        description = "description",
        rating = "rating",
        appAction = "action type",
        containerColor = Color.Cyan,
    )
}