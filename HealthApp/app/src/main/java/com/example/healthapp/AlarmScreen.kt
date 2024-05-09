package com.example.healthapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.healthapp.ui.theme.Dark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen(navController: NavController) {
    var dialog by remember {mutableStateOf(false)}
    Scaffold(
        topBar = { TopAppBar(title = { androidx.compose.material.Text("MY health data", color = Color.White, fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Dark), actions = {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription ="Vert", tint = Color.White )

        },)
        }, floatingActionButton = {
                                  FloatingActionButton(onClick = { dialog = true }, containerColor = Color.Black) {
                                      Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White )
                                  }
        },
    ) {
        if(dialog) {
            Dialog(onDismissRequest = { dialog = false }) {
                ApplicationDialog(
                    title = "Add alarm",
                    content = {

                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                                IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)) {
                                    Icon(painter = painterResource(R.drawable.food_dinner_svgrepo_com), contentDescription = "food", modifier = Modifier.size(30.dp) )
                                }
                                IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)) {
                                    Icon(painter = painterResource(R.drawable.water_drop_fill0_wght400_grad0_opsz24), contentDescription = "food", modifier = Modifier.size(30.dp) )
                                }
                                IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White), modifier = Modifier.size(30.dp)) {
                                    Icon(painter = painterResource(R.drawable.etc_svgrepo_com), contentDescription = "food",  )
                                }
                            }
                            Row {
                                Text(text = "18 hours", fontWeight = FontWeight.Bold, fontSize = 26.sp    )
                                Text(
                                    text = "5 mins",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp
                                )
                            }
                        }




                    },
                    onComplete = {dialog = false }) {
                    dialog = false
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Text(text = "Alarms", fontSize = 20.sp, modifier = Modifier.padding(10.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp )) {
                items(4) {
                    var dialog by remember {mutableStateOf(false)}
                    var checked by remember {mutableStateOf(false)}
                    var selected by remember {mutableStateOf("etc")}
                    if(dialog) {
                        Dialog(onDismissRequest = { dialog = false }) {
                            ApplicationDialog(
                                title = "Add alarm",
                                content = {

                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                                            IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)) {
                                                Icon(painter = painterResource(R.drawable.food_dinner_svgrepo_com), contentDescription = "food", modifier = Modifier.size(30.dp) )
                                            }
                                            IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)) {
                                                Icon(painter = painterResource(R.drawable.water_drop_fill0_wght400_grad0_opsz24), contentDescription = "food", modifier = Modifier.size(30.dp) )
                                            }
                                            IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White), modifier = Modifier.size(30.dp)) {
                                                Icon(painter = painterResource(R.drawable.etc_svgrepo_com), contentDescription = "food",  )
                                            }
                                        }
                                        Row {
                                            Text(text = "18 hours", fontWeight = FontWeight.Bold, fontSize = 26.sp    )
                                            Text(
                                                text = "5 mins",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 26.sp
                                            )
                                        }
                                    }




                                },
                                onComplete = {dialog = false }) {
                                dialog = false
                            }
                        }
                        }

                    Card(modifier = Modifier.fillMaxWidth(), onClick = {dialog = true}) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp  ), verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(R.drawable.food_dinner_svgrepo_com), contentDescription ="Food", modifier = Modifier
                                    .size(30.dp)
                                    .background(Color.White, shape = CircleShape) )
                                Text(text = "15:00", fontWeight = FontWeight.Bold, fontSize = 24.sp )
                            }

                        }
                    }
                }
            }

        }
    }

}