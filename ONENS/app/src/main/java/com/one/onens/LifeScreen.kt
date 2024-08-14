package com.one.onens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LifeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(painter = painterResource(id = R.drawable.life), contentDescription = "life", modifier = Modifier.align(
            Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "To accessy our c redits, visit\nGovwallet in the LifeSG app.",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Typews of credits avaliable:")


    }
}