package com.example.worldskillssingapore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.worldskillssingapore.ui.theme.RedColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun StallScreen(navController: NavController, string: String?) {



    var foods = remember { mutableStateListOf<Food>()}
    LaunchedEffect(Unit) {
        if (string != null) {
            APIManager().getFood(string, "1")?.let { foods.addAll(it) }
        }
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Stall 1", color = Color.White, fontWeight = FontWeight.SemiBold) }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White )
            }

        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = RedColor))

    }) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(foods.size) {

                    var swipeState = rememberSwipeableState(initialValue =false    )
                    LaunchedEffect(swipeState.currentValue) {
                        if(swipeState.currentValue) {
                            navController.navigate(
                                "addFood/${foods?.get(it)?.id}/${foods?.get(it)?.calorie}/${
                                    foods?.get(
                                        it
                                    )?.name
                                }/${
                                    foods?.get(it)?.color?.removePrefix(
                                        '#'.toString()
                                    )
                                }}"
                            )
                        }

                    }
                    Row(modifier = Modifier.offset(x = swipeState.offset.value.dp).swipeable(state = swipeState, anchors = mapOf(0f to false, 1000f to true), orientation = Orientation.Horizontal)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                "addFood/${foods?.get(it)?.id}/${foods?.get(it)?.calorie}/${
                                    foods?.get(
                                        it
                                    )?.name
                                }/${
                                    foods?.get(it)?.color?.removePrefix(
                                        '#'.toString()
                                    )
                                }}"
                            )

                        }
                        .padding(15.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        foods.get(it  )?.name?.let { it1 -> Text(text = it1, fontWeight = FontWeight.SemiBold) }
                        Row(horizontalArrangement = Arrangement.Center,modifier = Modifier
                            .width(90.dp)
                            .background(
                                Color(android.graphics.Color.parseColor("${foods?.get(it)?.color}")),
                                RoundedCornerShape(20.dp)
                            )) {
                            Text(text = foods?.get(it  )?.calorie + "Cal", color = Color.White)
                        }
                    }
                    Divider()
                }
            }
        }
    }
}