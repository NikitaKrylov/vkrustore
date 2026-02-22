package com.example.vkrustore.uikit.components

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
    modifier: Modifier = Modifier,
    onLeadingClick: (() -> Unit)? = null,
    onTrailingClick: (() -> Unit)? = null,
) {
    val searchBarState = rememberSearchBarState()

    SearchBar(
        modifier = modifier
            .fillMaxWidth(),
        state = searchBarState,
        tonalElevation = 0.dp,
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                expanded = false,
                onExpandedChange = {},
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
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = "search"
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = onTrailingClick ?: {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_mic_none_24),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = "voice search"
                        )
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
    )
}

@Preview
@Composable
fun PreviewTopSearchBar() {
    VKRuStoreTheme {
        TopSearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {}
        )
    }
}
