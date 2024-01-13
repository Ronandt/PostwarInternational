package com.example.international

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController, title: String, newsTitle: String?, skillName: String?, startDate: String?, endDate: String?, onShadow: (Boolean) -> Unit) {
    val context = LocalContext.current
    val jsonRetriever = JsonRetriever(context)

    var news = remember { mutableStateListOf<News>()}
    LaunchedEffect(key1 = Unit ) {

         news.addAll(jsonRetriever.retrieveNews().filter {

             title != "Search Results"|| (it.title.lowercase().contains(newsTitle?.lowercase().toString()) && it.skillName.lowercase().contains(skillName?.lowercase().toString()))
         })


    }
    Column() {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            Text("$title", fontWeight = FontWeight.SemiBold, modifier = Modifier.align(Alignment.CenterHorizontally))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(news.size) {

                    var dialogOpen by remember {mutableStateOf(false)}
                    Card(modifier= Modifier
                        .fillMaxWidth()
                        .height(150.dp), onClick = {
                        dialogOpen = true
                        onShadow(true)
                    }) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "${news[it].title}")
                            Text(text = it.toString())

                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "${news[it].skillName}")
                            Text(text = "${news[it].datetime}")
                        }
                        if(dialogOpen) {
                            Dialog(onDismissRequest = { dialogOpen = false
                            onShadow(false)}) {
                                ArticleDialog(news[it].title, news[it].datetime, news[it].description)
                            }
                        }
                    }
                }
            }
        }
    }




}

@Composable
fun ArticleDialog(title: String, datetime: String, description: String) {
    var textSize by remember {mutableStateOf(16.sp)}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Text(text = datetime, )
        Text(text = title)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { textSize =  textSize.times(0.8)}) {
                Icon(painterResource(id = R.drawable.baseline_minimize_24), contentDescription = "Minimise " )
            }
            IconButton(onClick = { textSize = textSize.times(1.2)}) {
                Icon(Icons.Default.Add, contentDescription = "Plus" )
            }
        }
        Text(text = description, fontSize = textSize)
    }
}