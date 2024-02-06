package com.example.international5

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.squareup.moshi.Json

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun SkillsScreen(navController: NavController) {
    var skillDialog by remember {mutableStateOf(-1)}
    val context = LocalContext.current
    var skills = remember { mutableStateListOf<Category>()}
    var skillsCategory by remember { mutableStateOf(-1)}
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getSkill()?.let { skills.addAll(it) }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {

        skills?.size?.let {
            items(it) { arr ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = skills[arr].category, fontSize = 34.sp )
            FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(skills[arr].trades.size) {
                    Row(modifier= Modifier
                        .width(300.dp)
                        .height(100.dp)
                        .clickable {
                            skillsCategory = arr
                            skillDialog = it
                        }
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = skills[arr].trades[it].skillName)
                        Icon(imageVector = Icons.Default.Add, contentDescription ="Add" )
                    }
                }
            }
            }
        }


    }

    if(skillDialog > -1) {
        BoxWithConstraints {
            if(maxWidth > maxHeight) {
                Dialog(onDismissRequest = { skillDialog = -1 }) {
                    Column(modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .fillMaxSize(0.9f)
                        .background(Color.White)
                        .padding(10.dp)) {
                        skills?.get(skillsCategory)?.trades?.get(skillDialog)
                            ?.let { Text(text = it.skillName, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth()) }

                        Spacer(modifier = Modifier.height(20.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            SelectionContainer {
                                skills?.get(skillsCategory)?.trades?.get(skillDialog)?.text?.let { Text(text = it, modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                  ) }
                            }

                            Column() {
                              Image(LoaderImage(skills?.get(skillsCategory)?.trades?.get(skillDialog)?.imageLink!!), contentDescription = "Image of skill", modifier = Modifier.size(160.dp, 200.dp))
                                Spacer(modifier = Modifier.weight(1f))
                                Button(onClick = {
                                    skillDialog +=1


                                }, modifier = Modifier.align(Alignment.End), enabled =    skills?.get(skillsCategory)?.trades?.getOrNull(skillDialog + 1) != null) {
                                    Text(text = "Next")
                                }
                            }
                        }

                    }
                }
            } else {
                Dialog(onDismissRequest = { skillDialog = -1
                skillsCategory = -1
                }) {
                    Column(modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .fillMaxSize(0.9f)
                        .background(Color.White)
                        .padding(10.dp)) {

                        skills?.get(skillsCategory)?.trades?.get(skillDialog)
                            ?.let { Text(text = it.skillName, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth()) }

                        Spacer(modifier = Modifier.height(20.dp))
                        SelectionContainer {
                            skills?.get(skillsCategory)?.trades?.get(skillDialog)?.text?.let { Text(text = it, modifier = Modifier.fillMaxWidth(0.9f)) }
                        }

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Image of skill", modifier = Modifier.size(160.dp, 200.dp))
                                Spacer(modifier = Modifier.weight(1f))
                                Button(onClick = {
                                    skillDialog +=1


                                }, modifier = Modifier.align(Alignment.End), enabled =    skills?.get(skillsCategory)?.trades?.getOrNull(skillDialog + 1) != null) {
                                    Text(text = "Next")
                                }
                            }


                    }
                }
            }


        }

    }
}