package com.example.vkrustore.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.vkrustore.core.navigation.BaseRoute
import com.example.vkrustore.uikit.spacing16

data class NavTabItem(
    val title: String,
    val iconRes: Int,
    val route: BaseRoute,
)


@Composable
fun AppNavigationBar(
    tabs: List<NavTabItem>,
    selectedRoute: BaseRoute?,
    onItemClick: (BaseRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .padding(horizontal = spacing16),
        containerColor = Color.Transparent,
    ) {
        tabs.forEach { tab ->
            val isSelected = selectedRoute == tab.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        onItemClick(tab.route)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(tab.iconRes),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                    )
                },
                alwaysShowLabel = true,
            )
        }
    }
}
