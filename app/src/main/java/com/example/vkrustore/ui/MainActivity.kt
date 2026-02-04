package com.example.vkrustore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkrustore.feature.account.api.AccountRoute
import com.example.vkrustore.feature.account.impl.AccountScreen
import com.example.vkrustore.feature.appDetail.api.AppDetailRoute
import com.example.vkrustore.feature.appDetail.impl.AppDetailScreen
import com.example.vkrustore.feature.categories.api.CategoriesRoute
import com.example.vkrustore.feature.categories.impl.CategoriesScreen
import com.example.vkrustore.feature.onboarding.api.OnboardingRoute
import com.example.vkrustore.feature.onboarding.impl.OnboardingScreen
import com.example.vkrustore.feature.search.api.SearchRoute
import com.example.vkrustore.feature.search.impl.SearchScreen
import com.example.vkrustore.feature.showcase.api.ShowcaseRoute
import com.example.vkrustore.feature.showcase.impl.ShowcaseScreen
import com.example.vkrustore.uikit.theme.VKRuStoreTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            VKRuStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = OnboardingRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<OnboardingRoute> {
                            OnboardingScreen(
                                onFinishOnboarding = {
                                   navController.navigate(ShowcaseRoute)
                                }
                            )
                        }

                        composable<ShowcaseRoute> {
                            ShowcaseScreen()
                        }

                        composable<CategoriesRoute> {
                            CategoriesScreen()
                        }

                        composable<SearchRoute> {
                            SearchScreen()
                        }

                        composable<AccountRoute> {
                            AccountScreen()
                        }

                        composable<AppDetailRoute> {
                            AppDetailScreen()
                        }
                    }

                }
            }
        }
    }
}
