package com.example.worldskillssingapore

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.worldskillssingapore.ui.theme.RedColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMyCaloriesScreen(navController: NavController) {
    val context = LocalContext.current
    var permissionLaunch = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        if(
            !it) {
            Toast.makeText(context, "We need permissions for us to show you the map", Toast.LENGTH_LONG).show()

        }
    }

    var foodCourts = remember { mutableStateListOf<FoodCourt>() }
    var foodCourtLetters = listOf("A", "B", "C")
    LaunchedEffect(Unit) {
        APIManager().getFoodCourts()?.let { foodCourts.addAll(it) }
    }

    LaunchedEffect(key1 = Unit) {
        permissionLaunch.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)

    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { androidx.compose.material3.Text(text = "F&B's near me", color = Color.White, fontWeight = FontWeight.SemiBold) }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White )
            }

        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = RedColor))

    }) {
        Column(modifier = Modifier.padding(it)) {
           AsyncImage   ("https://developers.onemap.sg/comm" +
                   "onapi/staticmap/getStaticImage?" +
                   "layerchosen=default&lat=1.30980&ln" +
                   "g=103.77750&zoom=14&height=512" +
                   "&width=400&points=[1.30980," +
                   "103.77750,\"175,50,0\",\"A\"]|" +
                   "[1.31141,103.77864,\"255,255,178\",\"" +
                   "B\"]|" +
                   "[1.30628,103.79036,\"175,50,0\",\"C\"]", contentDescription = "Map", modifier = Modifier.fillMaxWidth().height(300.dp   ), contentScale = ContentScale.Crop)
            Column(modifier = Modifier.background(Color.White)) {
                LazyColumn {
                    items(foodCourts.size) {
                        Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(10.dp  ), modifier = Modifier.fillMaxWidth().clickable {
                            navController.navigate("foodCourt/${foodCourts[it].ownercode}")

                        }.padding(15.dp   )) {
                            Text(
                                text = foodCourtLetters[it],
                                fontWeight = FontWeight.Bold,
                                color = RedColor,
                                fontSize = 20.sp
                            )


                            Text(text = "${foodCourts[it].name}", fontWeight = FontWeight.SemiBold, fontSize = 17.sp  )


                        }
                        Divider()
                    }
                }
            }
        }
        }
    }
