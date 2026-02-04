package com.example.vkrustore.feature.account.impl

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen() {
    val viewModel: AccountViewModel = koinViewModel()
}