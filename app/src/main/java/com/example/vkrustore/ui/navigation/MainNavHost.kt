package com.example.vkrustore.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.vkrustore.core.navigation.navigateNewTask
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


fun NavGraphBuilder.addOnboardingGraph(
    navController: NavHostController
) {
    navigation<OnboardingGraph>(
        startDestination = OnboardingRoute
    ) {
        composable<OnboardingRoute> {
            OnboardingScreen(
                onFinishOnboarding = {
                    navController.navigateNewTask(
                        route = ShowcaseGraph,
                        popUpTo = OnboardingGraph
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.addShowcaseGraph(
    navController: NavHostController
) {
    navigation<ShowcaseGraph>(
        startDestination = ShowcaseRoute
    ) {
        composable<ShowcaseRoute> {
            ShowcaseScreen(
                navigateToDetail = { appId ->
                    navController.navigate(AppDetailRoute(appId))
                }
            )
        }

        composable<AppDetailRoute> {
            AppDetailScreen(
                onBack = navController::popBackStack,
                showMessage = {}
            )
        }
    }
}

fun NavGraphBuilder.addSearchGraph(
    navController: NavController
) {
    navigation<SearchGraph>(
        startDestination = SearchRoute
    ) {
        composable<SearchRoute> {
            SearchScreen()
        }

        composable<AppDetailRoute> {
            AppDetailScreen(
                onBack = navController::popBackStack,
                showMessage = {}
            )
        }
    }
}

fun NavGraphBuilder.addCategoriesGraph(
    navController: NavController
) {
    navigation<CategoriesGraph>(
        startDestination = CategoriesRoute
    ) {
        composable<CategoriesRoute> {
            CategoriesScreen()
        }
    }
}