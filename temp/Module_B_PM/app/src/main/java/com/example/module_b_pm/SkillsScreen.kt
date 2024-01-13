
package com.example.module_b_pm

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.module_b_pm.data.Category
import com.example.module_b_pm.data.JsonRetriever

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SkillsScreen(navController: NavController, function: (Boolean) -> Unit) {
    var categories = remember {mutableStateListOf<Category>()}
    val context = LocalContext.current
    val repository = JsonRetriever(context)
    LaunchedEffect(Unit) {
        categories.addAll(repository.getSkills())
       // println(repository.getSkills())
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(20.dp)) {
            items(categories.size) { c->

                Text(text = "Skill type title", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(14.dp))
                FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    repeat(categories[c].skills.size) {
                        var dialog by remember {mutableStateOf(false    )}
                        
                        Card(modifier = Modifier
                            .height(80.dp)
                            .width(300.dp), onClick = {
                                dialog = true
                            function(true)
                        }) {
                            Text(text = "${categories[c].skills[it].name}", fontWeight = FontWeight.Bold)
                        }
                        if(dialog) {
                            var name by remember {mutableStateOf(categories[c].skills[it].name)}
                            var description by remember {mutableStateOf(categories[c].skills[it].description)}
                            LaunchedEffect(Unit) {
                                println(name)
                                println(description)
                            }
                            Dialog(onDismissRequest = { dialog = false
                                function(false)}) {
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Column {
                                          //  Text(text = name, fontSize = 20.sp )
                                            Spacer(modifier = Modifier.height(10.dp))
                                           // Text(text = description, modifier = Modifier.width(350.dp))
                                        }
                                        Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(20.dp)) {
                                            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Skill image", modifier = Modifier.size(300.dp, 200.dp) )
                                            Spacer(modifier = Modifier.weight(1f))
                                           Button(onClick = {
                                                name = categories[c].skills[it + 1].name
                                                description = categories[c].skills[it + 1].description
                                            }, enabled = categories[c].skills.getOrNull(it + 1) != null) {
                                                Text(text = "Next")
                                            }
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}