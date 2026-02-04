package com.example.vkrustore.uikit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun HorizontalAppCard(
    title: String,
    description: String,
    rating: String,
    actionType: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.love_vk),
            contentDescription = null,
            modifier = Modifier
                .size(68.dp)
        )

        Spacer(Modifier.size(10.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = "App name",
                fontSize = 18.sp
            )
            Text(
                text = "al;fjldksjf al;sdfjklajsdsadfj lkajs lkfjsalk sdfljsdlkfjslf",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
            )
            Text(
                text = "5+"
            )

        }

        Spacer(Modifier.size(10.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .height(32.dp)
        ) {
            Text(
                text = "Download"
            )
        }

    }
}


@Preview
@Composable
private fun HorizontalAppCardPreview() {
    VKRuStoreTheme {
        HorizontalAppCard(
            title = "title",
            description = "description",
            rating = "rating",
            actionType = "action type",
            onClick = {  },
        )
    }
}

