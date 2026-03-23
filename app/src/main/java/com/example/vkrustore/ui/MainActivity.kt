package com.example.vkrustore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.vkrustore.core.storage.KeyValueStorage
import com.example.vkrustore.core.storage.StorageKeys
import com.example.vkrustore.core.storage.get
import com.example.vkrustore.ui.components.AppNavigationBar
import com.example.vkrustore.ui.components.NavTabItem
import com.example.vkrustore.ui.navigation.CategoriesGraph
import com.example.vkrustore.ui.navigation.OnboardingGraph
import com.example.vkrustore.ui.navigation.SearchGraph
import com.example.vkrustore.ui.navigation.ShowcaseGraph
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
                    route = ShowcaseGraph,
                ),
                NavTabItem(
                    title = "Поиск",
                    iconRes = UiKit.drawable.baseline_search_24,
                    route = SearchGraph,
                ),
                NavTabItem(
                    title = "Категории",
                    iconRes = UiKit.drawable.baseline_star_24,
                    route = CategoriesGraph,
                )
            )
            val snackbarHostState = remember { SnackbarHostState() }

            VKRuStoreTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(0),
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    },
                    bottomBar = {
                        AppNavigationBar(navTabs, navController)
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