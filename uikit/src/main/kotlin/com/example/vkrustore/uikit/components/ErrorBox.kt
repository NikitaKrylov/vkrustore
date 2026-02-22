package com.example.vkrustore.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.spacing12
import com.example.vkrustore.uikit.spacing24
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

@Composable
fun ErrorBox(
    description: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: @Composable (ColumnScope.() -> Unit)? = null,
    button: @Composable (ColumnScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon?.let {
            it(this)

            Spacer(Modifier.height(spacing12))
        }

        title?.let {
            Text(
                text = title,
                style = TextStyles.TitleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = description,
            style = TextStyles.BodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        button?.let {
            Spacer(Modifier.height(spacing24))

            it(this)
        }
    }
}

@Preview
@Composable
fun PreviewErrorBox() {
    VKRuStoreTheme(darkTheme = true) {
        ErrorBox(
            title = "Error",
            description = "Sorry",
            icon = {
                Icon(
                    painter = painterResource(R.drawable.geometric),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            button = {
                PrimaryButton(
                    text = "update",
                    onClick = {}
                )
            }
        )
    }
}