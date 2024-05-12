package com.example.worldskills_c_am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InboxScreen(navController: NavController) {
    var list = listOf("Notifications", "Messages")
    var pagerState = rememberPagerState(0) {
        list.size
    }
Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)) {
    TabRow(selectedTabIndex = pagerState.currentPage) {
        list.forEach {

        }
    }
}
}