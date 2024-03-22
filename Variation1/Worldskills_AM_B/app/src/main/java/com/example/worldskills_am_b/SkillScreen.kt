package com.example.worldskills_am_b

import android.graphics.drawable.GradientDrawable.Orientation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillScreen(navController: NavController) {
    var typeOfCategory by remember {mutableStateOf<Int?>(null)}
    var typeOfSkill by remember {mutableStateOf<Int?>(null)}
    val context = LocalContext.current
    var categoryList = remember {mutableStateListOf<Category>()}
    LaunchedEffect(Unit) {
        var jsonmanager = JsonManager(context)
        jsonmanager.skills()?.let { categoryList.addAll(it) }
        println(jsonmanager.skills())
        println("WHY DOES THIS HAPPEN ")

    }

    var localView = LocalView.current

TemplateContent({
LazyColumn {
    items(categoryList.size) { a->
        Column {
            Row(modifier = Modifier
                .height(40.dp)
                .width(300.dp)
                .background(Color.Black)) {
                Spacer(modifier = Modifier.height(40.dp))
                androidx.compose.material.Text(text = categoryList[a].category, color = Color.White, fontSize = 30.sp )
            }
            Spacer(modifier = Modifier.height(40.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp   ), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(categoryList[a].trades.size) {
                    var openDialog by remember {mutableStateOf(false)}
                    Card(onClick = {
                                   openDialog = true
                        typeOfCategory = a
                        typeOfSkill = it
                    }, modifier = Modifier
                        .width(300.dp)
                        .height(100.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            androidx.compose.material3.Text(text = categoryList[a].trades[it].skillName)
                            Icon(imageVector = Icons.Default.Add, contentDescription = "View more " )
                        }

                    }


                    if(openDialog) {
                        Dialog(onDismissRequest = { openDialog = false }) {
                            if(localView.width < localView.height) {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)){


                                    Column(modifier = Modifier
                                        .padding(10.dp)
                                        .width(300.dp)){
                                        androidx.compose.material3.Text(text = categoryList[typeOfCategory!!].trades[typeOfSkill!!].skillName, fontWeight = FontWeight.SemiBold, fontSize = 20.sp )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        SelectionContainer {
                                            androidx.compose.material3.Text(text = categoryList[typeOfCategory!!].trades[typeOfSkill!!].text  )
                                        }

                                    }
                                    Column(modifier = Modifier.padding(10.dp)){
                                        AsyncImage(model  = categoryList[typeOfCategory!!].trades[typeOfSkill!!].imageLink, contentDescription = "Image of skill", contentScale = ContentScale.Crop, modifier = Modifier.size(300.dp))
                                        Spacer(modifier = Modifier.weight(1f))
                                        Button(enabled = categoryList[typeOfCategory!!].trades.size > typeOfSkill!! + 1,onClick = {

                                                         typeOfCategory = a
                                            typeOfSkill = typeOfSkill!! + 1
                                        }, modifier = Modifier
                                            .padding(6.dp)
                                            .align(Alignment.End)) {


                                            Text(text = "Next")
                                        }
                                    }

                                }
                            } else {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)){

                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(40.dp  )) {
                                        Column(modifier = Modifier
                                            .padding(10.dp)
                                            .width(300.dp)){
                                            androidx.compose.material3.Text(text =categoryList[typeOfCategory!!].trades[typeOfSkill!!].skillName, fontWeight = FontWeight.SemiBold, fontSize = 20.sp )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            SelectionContainer() {
                                                androidx.compose.material3.Text(text = categoryList[typeOfCategory!!].trades[typeOfSkill!!].text)
                                            }

                                        }
                                        Column(modifier = Modifier.padding(10.dp)){
                                            AsyncImage(model  = categoryList[typeOfCategory!!].trades[typeOfSkill!!].imageLink, contentDescription = "Image of skill", contentScale = ContentScale.Crop, modifier = Modifier.size(300.dp))
                                            Spacer(modifier = Modifier.weight(1f))
                                            Button(enabled = categoryList[typeOfCategory!!].trades.size > typeOfSkill!! + 1,onClick = {

                                                typeOfCategory = a
                                                typeOfSkill = typeOfSkill!! + 1
                                            }, modifier = Modifier
                                                .padding(6.dp)
                                                .align(Alignment.End)) {


                                                Text(text = "Next")
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
}



})

}