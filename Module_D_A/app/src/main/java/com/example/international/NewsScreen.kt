package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.international.ui.theme.Dim
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController, title: String, newsTitle: String?, skillName: String?, startDate: String?, endDate: String?, onShadow: (Boolean) -> Unit) {
    val context = LocalContext.current
    val jsonRetriever = JsonRetriever(context)

    var news = remember { mutableStateListOf<NewsItem?>()}
    LaunchedEffect(key1 = Unit ) {
        println("HERE")
        println(WorldSkillsAPI().getNewsInfo())
      var cache =  WorldSkillsAPI().getNewsInfo()
        (0..cache?.toList()?.map {it.subcategories}?.size!!).forEach {
            cache?.toList()?.map {it.subcategories}?.getOrNull(it)?.filter { title == "Search Results" || it.subcategory == title }?.map { it.newsItems }
                ?.getOrNull(0)?.filter{(startDate == null || formatDate(it.time.split(" ").drop(1).joinToString(" ")) == startDate)}?.let { news.addAll(it) }
        }


    }
    Column() {

        Column(modifier = Modifier
            .fillMaxSize()
            ) {
            Box(modifier = Modifier.fillMaxWidth()) {

                Image(painter = painterResource(R.drawable.cloud_cop), contentDescription = "Skill image", modifier = Modifier
                    .blur(10.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .graphicsLayer {

                    }, contentScale = ContentScale.Crop, )
                Spacer(modifier = Modifier.matchParentSize().background(Dim))
                Column( modifier = Modifier.align(Alignment.Center)) {
                    Image(painter = painterResource(R.drawable.cube_3), contentDescription = null, modifier = Modifier
                        .size(34.dp)
                        .align(Alignment.CenterHorizontally))
                    
                    Text("$title", fontWeight = FontWeight.SemiBold, fontSize = 24.sp, color = Color.White)

                }

            }

            Spacer(modifier = Modifier.height(14.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier =  Modifier.padding(10.dp)) {
                items(news.size) {

                    var dialogOpen by remember {mutableStateOf(false)}
                    Column(modifier= Modifier
                        .fillMaxWidth()
                        .height(150.dp).clickable {
                            dialogOpen = true
                            onShadow(true)
                        }) {
                        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "${news[it]?.title}")
                                Text(text = (it+1).toString())

                            }

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "${title}")
                                Text(text = "${news[it]?.time}")
                            }
                        }
                 

                    }
                    if(dialogOpen) {
                        Dialog(onDismissRequest = { dialogOpen = false
                            onShadow(false)}) {
                            news[it]?.description?.let { it1 ->
                                news[it]?.time?.let { it2 ->
                                    news[it]?.title?.let { it3 ->
                                        ArticleDialog(
                                            it3, it2,
                                            it1
                                        )
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


fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val date: Date = inputFormat.parse(inputDate) ?: Date()
    return outputFormat.format(date)
}


