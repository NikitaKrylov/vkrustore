package com.example.vkrustore.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vkrustore.core.navigation.BaseRoute
import com.example.vkrustore.core.navigation.navigateNewTask
import com.example.vkrustore.feature.categories.api.CategoriesRoute
import com.example.vkrustore.feature.search.api.SearchRoute
import com.example.vkrustore.feature.showcase.api.ShowcaseRoute
import com.example.vkrustore.uikit.TextStyles
import com.example.vkrustore.uikit.theme.VKRuStoreTheme
import com.example.vkrustore.uikit.R as UiKit

data class NavTabItem(
    val title: String,
    val iconRes: Int,
    val route: BaseRoute,
)


@Composable
fun AppNavigationBar(
    tabs: List<NavTabItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val outlineColor = MaterialTheme.colorScheme.outlineVariant
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val selectedRoute = remember(currentDestination) {
        tabs
            .firstOrNull { tab ->
                currentDestination?.hierarchy?.any { it.hasRoute(tab.route::class) } == true
            }?.route
    }
    val isNavigationBarVisible = remember(selectedRoute) {
        selectedRoute != null
    }

    AnimatedVisibility(
        visible = isNavigationBarVisible,
        label = "Navigation bar visibility",
        enter = fadeIn() + slideInVertically { it },
        exit = fadeOut() + slideOutVertically { it },
    ) {
        NavigationBar(
            modifier = modifier
                .background(MaterialTheme.colorScheme.surface)
                .navigationBarsPadding()
                .height(68.dp)
                .drawBehind {
                    drawLine(
                        color = outlineColor,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 1.dp.toPx()
                    )
                },
            containerColor = Color.Transparent,
            windowInsets = WindowInsets(0)
        ) {
            tabs.forEach { tab ->
                val isSelected = selectedRoute == tab.route

                CompactNavItem(
                    selected = isSelected,
                    onClick = { if (!isSelected) navController.navigateNewTask(tab.route, tab.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    label = {
                        Text(
                            text = tab.title,
                            fontSize = 8.sp,
                            style = TextStyles.LabelSmall
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RowScope.CompactNavItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    spacing: Dp = 2.dp,
    label: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 4.dp),
    rippleColor: Color = MaterialTheme.colorScheme.primary,
    boundedRipple: Boolean = false,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
) {
    val density = LocalDensity.current
    val interactionSource = remember { MutableInteractionSource() }
    var itemWidth by remember { mutableIntStateOf(0) }
    val iconColor = colors.selectedIconColor.takeIf { selected } ?: colors.unselectedIconColor
    val textColor = colors.selectedTextColor.takeIf { selected } ?: colors.unselectedTextColor
    val rippleRadius = with(density) {
        (itemWidth / 2 * 1.5f).toDp()
    }

    Box(
        modifier = modifier
            .onSizeChanged { itemWidth = it.width }
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = ripple(
                    bounded = boundedRipple,
                    radius = rippleRadius,
                    color = rippleColor
                )
            )
            .weight(1f)
    ) {
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing, Alignment.CenterVertically)
        ) {
            CompositionLocalProvider(LocalContentColor provides iconColor) {
                icon()
            }

            label?.let {
                CompositionLocalProvider(
                    LocalContentColor provides textColor
                ) {
                    label()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppNavigationBar() {
    val navTabs = listOf(
        NavTabItem(
            title = "Приложения",
            iconRes = UiKit.drawable.round_android_24,
            route = ShowcaseRoute,
        ),
        NavTabItem(
            title = "Поиск",
            iconRes = UiKit.drawable.baseline_search_24,
            route = SearchRoute,
        ),
        NavTabItem(
            title = "Категории",
            iconRes = UiKit.drawable.baseline_star_24,
            route = CategoriesRoute,
        )
    )

    VKRuStoreTheme() {
        AppNavigationBar(
            tabs = navTabs,
            navController = rememberNavController()
        )
    }
}
