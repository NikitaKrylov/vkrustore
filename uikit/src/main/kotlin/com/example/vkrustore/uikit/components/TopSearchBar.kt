package com.example.vkrustore.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.smallShape
import com.example.vkrustore.uikit.spacing16
import com.example.vkrustore.uikit.theme.VKRuStoreTheme
import com.example.vkrustore.uikit.topBarHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit),
) {
    val customStyle = MaterialTheme.typography.bodyLarge.copy(
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        lineHeight = 0.sp
    )

    SearchBar(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        shape = RectangleShape,
        windowInsets = WindowInsets(top = 0.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        inputField = {
            CompositionLocalProvider(LocalTextStyle provides customStyle) {
                SearchBarDefaults.InputField(
                    modifier = Modifier
                        .padding(horizontal = spacing16)
                        .background(
                            shape = RoundedCornerShape(smallShape),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                        .height(topBarHeight),
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = onSearch,
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = {
                        Text(
                            text = "Поиск в RuStore",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = TextStyles.LabelLarge
                        )
                    },
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
        }
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewTopSearchBar() {
    VKRuStoreTheme(darkTheme = true) {
        TopSearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            expanded = true,
            onExpandedChange = {}
        ) {}
    }
}
