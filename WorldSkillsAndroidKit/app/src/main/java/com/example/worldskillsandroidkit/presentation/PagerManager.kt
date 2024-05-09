package com.example.worldskillsandroidkit.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable

class PagerManager() {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
fun rememberDefaultPagerState(size: Int): PagerState {
    return rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f)
}
}