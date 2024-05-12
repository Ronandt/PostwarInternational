package com.example.worldskillssingapore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.worldskillssingapore.ui.theme.RedColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCourtScreen(navController: NavController, string: String?) {
    var merchants = remember { mutableStateListOf<Merchants>() }
    LaunchedEffect(Unit) {
        if (string != null) {
            APIManager().getMerchants(string, "1")?.let { merchants.addAll(it) }
        }
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "FoodCourtWSS2021", color = Color.White, fontWeight = FontWeight.SemiBold) }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White )
            }

        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = RedColor))

    }) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(merchants.size) {
                    Row(modifier = Modifier.clickable {
                        navController.navigate("stall/${merchants   [it].id}")

                    }.padding(15.dp   )) {
                        Text(text = "${merchants[it].name}", fontWeight = FontWeight.SemiBold)
                    }
                    Divider()
                }
            }
        }
    }
}