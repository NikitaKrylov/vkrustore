package com.example.vkrustore.uikit.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkrustore.uikit.R
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.mediumShape
import com.example.vkrustore.uikit.theme.VKRuStoreTheme

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
    onLeadingClick: (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTrailingClick: (() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit),
) {
    SearchBar(
        modifier = modifier
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {
                    onSearch(it)
                },
                expanded = expanded,
                onExpandedChange = onExpandedChange,
                placeholder = {
                    Text(
                        text = "Поиск в RuStore",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = TextStyles.LabelLarge
                    )
                },
                leadingIcon = {
                    IconButton(
                        onClick = onLeadingClick ?: {}
                    ) {
                        leadingIcon?.let { it() }
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = onTrailingClick ?: {}
                    ) {
                        trailingIcon?.let { it() }
                    }
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
        },
        shape = RoundedCornerShape(mediumShape),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewTopSearchBar() {
    VKRuStoreTheme {
        TopSearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            expanded = true,
            onExpandedChange = {}
        ) {}
    }
}
