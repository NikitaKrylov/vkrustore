package com.example.vkrustore.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.PinnableContainer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.theme.VKRuStoreTheme
import kotlin.math.acos


@Composable
fun ExpandedAppCard(
    title: String,
    description: String,
    rating: String,
    appAction: String,
    containerColor: Color,
    modifier: Modifier = Modifier,
    bannerHead: String? = null,
    bannerSubhead: String? = null,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(containerColor)
            .height(300.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0x4D000000))
                    )
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomCenter)
        ) {
            bannerHead?.let {
                Text(
                    text = bannerHead,
                    fontSize = 24.sp,
                )
            }

            bannerSubhead?.let {
                Text(
                    text = bannerSubhead,
                    fontSize = 15.sp,
                )
            }

            HorizontalAppCard(
                title = title,
                description = description,
                rating = rating,
                actionType = appAction,
                onClick = {},
                painter = painterResource(R.drawable.love_vk)
            )
        }
    }

}


@Preview
@Composable
private fun ExpandedAppCardPreview() {
    VKRuStoreTheme {
        ExpandedAppCard(
            bannerHead = "Banner head",
            bannerSubhead = "Banner subhead",
            title = "title",
            description = "description",
            rating = "5",
            appAction = "action type",
            containerColor = Color.Cyan,
        )
    }
}