package com.example.international_a_am

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun Back(navController: NavController, modifier: Modifier) {
    IconButton(onClick = { navController.navigateUp()}, modifier = modifier) {
        Icon(painterResource(R.drawable.baseline_arrow_back_24), contentDescription = "Go back" )
    }
}