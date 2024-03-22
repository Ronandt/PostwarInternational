package edu.ws2022.aXX.pm

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsScreen(navController: NavController) {
    var skills = remember { mutableStateListOf<Category>()}
    val context = LocalContext.current
    val view = LocalView.current
    var horizontal = view.width > view.height
LaunchedEffect(Unit) {
    JsonManager(context).getSkillsAndCategory()?.let { skills.addAll(it) }

}
Column(modifier = Modifier
    .fillMaxSize()
    .padding(20.dp)
) {
    LazyColumn {
        items(skills.size) { categoryIndex ->
            var skillCategoryDialog by remember {mutableStateOf(false)}
            var tradeDialogIndex by remember {mutableStateOf(0)}

            Column {

                Row(modifier = Modifier
                    .width(200.dp)
                    .background(Color.Black)) {
                    Text(text = skills[categoryIndex].category, modifier = Modifier.padding(10.dp   ), color = Color.White )
                }
                FlowRow(maxItemsInEachRow = if(horizontal)3 else 2) {
                    repeat(skills[categoryIndex].trades.size) { tradeIndex ->
                        Row(modifier = Modifier
                            .width(250.dp)
                            .padding(20.dp)
                            .clickable {
                                tradeDialogIndex = tradeIndex
                                skillCategoryDialog = true

                            }, horizontalArrangement = Arrangement.SpaceBetween){

                            Text(text = skills[categoryIndex].trades[tradeIndex].skillName)
                            Icon(imageVector = Icons.Default.Add, contentDescription ="Add" )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(30.dp))

            }
            if(skillCategoryDialog) {

                Dialog(onDismissRequest = {skillCategoryDialog = false }) {
                    if(horizontal) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .background(Color.White), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            Column(modifier = Modifier.width(220.dp    )) {
                                Text(text = skills[categoryIndex].trades[tradeDialogIndex].skillName, fontWeight = FontWeight.Bold, fontSize = 26.sp    )
                                SelectionContainer {
                                    Text(text =  skills[categoryIndex].trades[tradeDialogIndex].text    )
                                }

                            }
                            Column(modifier = Modifier.width(220.dp )) {
                                Spacer(modifier = Modifier.height(20.dp))
                                AsyncImage(model =  skills[categoryIndex].trades[tradeDialogIndex].imageLink,  contentDescription = "image of skill", contentScale = ContentScale.Crop, modifier = Modifier
                                    .height(200.dp)
                                    .width((300.dp)))
                                Spacer(modifier = Modifier.weight(1f))
                                Button(onClick = {
                                    tradeDialogIndex += 1
                                },modifier = Modifier
                                    .padding(10.dp)
                                    .align(Alignment.End), enabled = tradeDialogIndex < skills[categoryIndex].trades.size -1 ) {
                                    Text(text = "Next")
                                }
                            }

                        }
                    } else {
                        Column(modifier = Modifier
                            .padding(10.dp)
                            .height(700.dp)
                            .width(500.dp)) {
                            Text(text = skills[categoryIndex].trades[tradeDialogIndex].skillName, fontWeight = FontWeight.Bold, fontSize = 26.sp    )
                            Spacer(modifier = Modifier.height(30.dp))
                            AsyncImage(model  =  skills[categoryIndex].trades[tradeDialogIndex].imageLink, contentDescription = "image of skill", contentScale = ContentScale.Crop, modifier = Modifier
                                .height(200.dp)
                                .width((300.dp)))
                            SelectionContainer {
                                Text(text = skills[categoryIndex].trades[tradeDialogIndex].text)

                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(onClick = { tradeDialogIndex += 1 }, enabled = tradeDialogIndex < skills[categoryIndex].trades.size -1) {
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