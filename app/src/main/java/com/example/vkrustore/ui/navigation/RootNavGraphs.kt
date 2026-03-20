package com.example.vkrustore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.vkrustore.core.navigation.BaseRoute
import kotlinx.serialization.Serializable

@Serializable
data object OnboardingGraph : BaseRoute

@Serializable
data object ShowcaseGraph : BaseRoute

@Serializable
data object SearchGraph : BaseRoute

@Serializable
data object CategoriesGraph : BaseRoute

