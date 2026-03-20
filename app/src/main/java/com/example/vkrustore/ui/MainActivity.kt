package com.example.vkrustore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vkrustore.ui.navigation.OnboardingGraph
import com.example.vkrustore.ui.navigation.ShowcaseGraph
import com.example.vkrustore.core.navigation.navigateNewTask
import com.example.vkrustore.core.storage.KeyValueStorage
import com.example.vkrustore.core.storage.StorageKeys
import com.example.vkrustore.core.storage.get
import com.example.vkrustore.feature.categories.api.CategoriesRoute
import com.example.vkrustore.feature.search.api.SearchRoute
import com.example.vkrustore.feature.showcase.api.ShowcaseRoute
import com.example.vkrustore.ui.components.AppNavigationBar
import com.example.vkrustore.ui.components.NavTabItem
import com.example.vkrustore.ui.navigation.addCategoriesGraph
import com.example.vkrustore.ui.navigation.addOnboardingGraph
import com.example.vkrustore.ui.navigation.addSearchGraph
import com.example.vkrustore.ui.navigation.addShowcaseGraph
import com.example.vkrustore.uikit.theme.VKRuStoreTheme
import org.koin.android.ext.android.inject
import com.example.vkrustore.uikit.R as UiKit

class MainActivity : ComponentActivity() {
    private val storage: KeyValueStorage by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
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

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val selectedRoute = remember(currentDestination) {
                navTabs
                    .map(NavTabItem::route)
                    .firstOrNull { route ->
                        currentDestination?.hasRoute(route::class) == true
                    }
            }
            val isNavigationBarVisible = remember(selectedRoute) {
                selectedRoute in navTabs.map(NavTabItem::route)
            }

            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            VKRuStoreTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(0),
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isNavigationBarVisible,
                            label = "Navigation bar visibility",
                            enter = fadeIn() + slideInVertically { it },
                            exit = fadeOut() + slideOutVertically { it },
                        ) {
                            AppNavigationBar(
                                tabs = navTabs,
                                selectedRoute = selectedRoute,
                                onItemClick = { route ->
                                    navController.navigateNewTask(route, route)
                                },
                            )
                        }
                    }
                ) { innerPadding ->
                    val isOnboarding = storage.get<Boolean>(StorageKeys.IsOnboarded) ?: true

                    NavHost(
                        navController = navController,
                        startDestination = OnboardingGraph.takeIf { isOnboarding } ?: ShowcaseGraph,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        addOnboardingGraph(navController)
                        addShowcaseGraph(navController)
                        addSearchGraph(navController)
                        addCategoriesGraph(navController)
                    }
                }
            }
        }
    }
}