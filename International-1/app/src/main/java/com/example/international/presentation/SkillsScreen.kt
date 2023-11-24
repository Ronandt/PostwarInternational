package com.example.international.presentation

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.example.international.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsScreen(navController: NavController) {

    Column {
        Text(text = "Skill type title", fontSize = 30.sp,)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(1) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    maxItemsInEachRow = 3,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    repeat(9) {
                        var openDialog by remember {mutableStateOf(false)}
                        Row(modifier = Modifier
                            .width(250.dp)
                            .height(60.dp)
                            .background(Color.Gray)
                            .padding(10.dp)
                            .clickable {
                                openDialog = true

                            }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Skill name")
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }

                        if (openDialog) {
                            Dialog(onDismissRequest = { openDialog = false},) {
                                Row( modifier = Modifier
                                    .fillMaxHeight(0.8f).fillMaxWidth()
                                    .background(Color.White).padding(10.dp), horizontalArrangement = Arrangement.SpaceAround) {
                                    Column(modifier = Modifier.weight(0.5f)) {
                                        Text("Skill name", fontWeight = FontWeight.Bold, fontSize =30.sp)
                                        Text("Lorem ipsum doloa Lorem ipsum doloa Lorem ipsum doloa Lorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloaLorem ipsum doloa", fontWeight = FontWeight.Bold)
                                    }
                                    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight())     {
                                        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier
                                            .height(300.dp)
                                            .width(250.dp) )

                                        Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                                            Text(text = "Next", fontSize = 24.sp)
                                        }

                                    }

                                }

                            }
                        }
                    }

                }
            }

        }
    }



}

