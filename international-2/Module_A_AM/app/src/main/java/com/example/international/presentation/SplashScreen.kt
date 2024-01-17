package com.example.international.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.international.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = Unit ) {
        delay(2000)
        navController.navigate("events")
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painterResource(id = R.drawable.wsw), modifier = Modifier.size(100.dp), contentScale = ContentScale.Crop, contentDescription = "")
    }
}
//GG MY BRAIN WAS NOT IN THE BEST MELT EIOFEOJIFEIOJ IOJTO CODEDE TODAY FOUISDJOFWEJIOFEJIOF WEJIOF WEJIOF EWJIOF EJWIOFEJ8iofejiofejiof