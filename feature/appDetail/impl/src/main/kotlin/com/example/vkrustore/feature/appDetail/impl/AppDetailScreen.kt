package com.example.vkrustore.feature.appDetail.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vkrustore.feature.appDetail.impl.components.AppDetail
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppDetailScreen(

) {
    val viewModel: AppDetailViewModel = koinViewModel()

    AppDetail(

    )
}